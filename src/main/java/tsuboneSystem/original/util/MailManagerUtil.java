package tsuboneSystem.original.util;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.StringUtil;

import tsuboneSystem.code.ActorKindCode;
import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;

public class MailManagerUtil {

	//送信者のメンバーID
	public Integer fromMemberId;
	
	//送信先アドレス
	ArrayList<TMember> toAddressAdmin = new ArrayList<TMember>();
	//送信先アドレス
	ArrayList<TMember> toAddressLeaders = new ArrayList<TMember>();
	//送信先アドレス
	ArrayList<TMember> toAddressInd = new ArrayList<TMember>();

	//内容
	public String content = null;

	//タイトル
	public String title = null;
	
	//送信者のmemberID
	public Integer sendMemberID;
	
	//送信結果
	public boolean error = false;
	
	//送信結果を登録したTMail
	public TMail tMail = new TMail();
	
	//機能名
	public String contentName = null;
	
	//メールに記載する遷移先のId
	public Integer contentId = null;
	
	//メールに記載するリンクurlの説明
	public String caption = null;
	
	//メールにリンクをつけるかどうか
	public boolean linkUrlFlag = false;
	
	//urlの先頭
	private final String urlHead = "http://kagucho.net/";
	
	//メールの回覧権限(デフォルトはMEMBER)
	public Integer browsingRights = MailBrowsingRightsCode.MEMBER.getCodeNumber();
	
	
	/**
	 * タイトルを追加する。
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 現在の内容に追加する。
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * メールに記載したいURLにつける機能id
	 * @param contentId
	 */
	public void setContentId(Integer contentId){
		this.contentId = contentId;
	}
	
	/**
	 * メールに記載したい機能名
	 * 
	 * @param requestUrl
	 */
	public void setContentName(String contentName){
		this.contentName = contentName;
	}
	
	/**
	 * メールに記載したいリンクの説明
	 * 
	 * @param caption
	 */
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	/**
	 * メール送信者のID
	 * @param sendMemberID
	 */
	public void setRegistId(Integer sendMemberID) {
		this.sendMemberID = sendMemberID;
	}
	
	/**
	 * メールにリンクをつけるかどうか
	 * @param linkUrlFlag
	 */
	public void setLinkUrlFlag(boolean linkUrlFlag){
		this.linkUrlFlag = linkUrlFlag;
	}
	
	/**
	 * メールの回覧種別
	 * @param linkUrlFlag
	 */
	public void setBrowsingRights(Integer browsingRights){
		this.browsingRights = browsingRights;
	}
	
	/**
	 * 送信結果
	 * @param error
	 */
	public boolean getSendMailResult(){
		return error;
	}
	
	/**
	 * メールを保存したTMail
	 * @param TMail
	 */
	public TMail getTMail(){
		return tMail;
	}

	/**
	 * アドレスを設定する。権限によって3分割する。
	 * @param toAddress
	 */
	public void setToAddressActorSplit(List<TMember> memberList) {
		for (TMember tMember : memberList) {
			String actorKind = TsuboneSystemUtil.actorKind(tMember);
			if(ActorKindCode.ADMIN.getCode().equals(actorKind)){
				toAddressAdmin.add(tMember);
			}else if(ActorKindCode.LEADERS.getCode().equals(actorKind)){
				toAddressLeaders.add(tMember);
			}else if(ActorKindCode.MEMBER.getCode().equals(actorKind)){
				toAddressInd.add(tMember);
			}
		}
	}
	//メールを送信する
	public String sendMail(){
		
		String rtnMesse = new String();
		
		MailManager mailManager = SingletonS2Container.getComponent(MailManager.class);
		mailManager.setTitle(title);
		mailManager.setContent(content);
		
		//管理者に対して
		if(!toAddressAdmin.isEmpty()){
			mailManager.setSendMemberId(sendMemberID);
			mailManager.setContent(getContentUrlFactory(ActorKindCode.ADMIN.getCode()));
			mailManager.setToAddress(toAddressAdmin.toArray(new TMember[0]));
			error = mailManager.sendMail();
		}
		//leadersに対して
		if(!toAddressLeaders.isEmpty()){
			mailManager.setSendMemberId(sendMemberID);
			mailManager.setContent(getContentUrlFactory(ActorKindCode.LEADERS.getCode()));
			mailManager.setToAddress(toAddressLeaders.toArray(new TMember[0]));
			error = mailManager.sendMail();
		}
		//一般メンバーに対して
		if(!toAddressInd.isEmpty()){
			mailManager.setSendMemberId(sendMemberID);
			mailManager.setContent(getContentUrlFactory(ActorKindCode.MEMBER.getCode()));
			mailManager.setToAddress(toAddressInd.toArray(new TMember[0]));
			error = mailManager.sendMail();
		}
		//ログを残す
		setLog(error);
		
		if (error) {
			rtnMesse = "メールの送信に失敗しました";
		}else{
			rtnMesse = "メールの送信に成功しました";
		}
		
		return rtnMesse;
	}
	
	/**
	 * DBにログを残す
	 * @param notError
	 */
	@SuppressWarnings("deprecation")
	private void setLog(boolean notError) {
		
		//以下メールの送信履歴を残す
		tMail.title = title;
		tMail.content = content;
		tMail.errorFlag = notError;
		tMail.registMemberId = sendMemberID;
		tMail.browsingRights = browsingRights;
		//本来はここで挿入を行うべきなので問題ない
		TMailService tMailService = SingletonS2Container.getComponent(TMailService.class);
		tMailService.insert(tMail);
	
		ArrayList<TMember> toAddress = new ArrayList<TMember>();
		toAddress.addAll(toAddressAdmin);
		toAddress.addAll(toAddressLeaders);
		toAddress.addAll(toAddressInd);
		for (TMember tMemberOne : toAddress) {
			TMailSendMember tMailSendMember = new TMailSendMember();
			tMailSendMember.mailId = tMail.id;
			tMailSendMember.memberId = tMemberOne.id;
			//本来はここで挿入を行うべきなので問題ない
			TMailSendMemberService tMailSendMemberService = SingletonS2Container.getComponent(TMailSendMemberService.class);
			tMailSendMemberService.insert(tMailSendMember);
		}	
	}
	
	/**
	 * 渡された機能urlと機能idからactorごとのリンクurlをつくる
	 * @param url
	 */
	public String getContentUrlFactory(String actorKindCode){
		
		StringBuffer urlbf = new StringBuffer();
		urlbf.append(content);
		if(linkUrlFlag){
			urlbf.append("\n");
			if (StringUtil.isNotEmpty(caption)) {
				urlbf.append(caption);
			}
			urlbf.append("\n");
			urlbf.append(urlHead);
			if(ActorKindCode.ADMIN.getCode().equals(actorKindCode)){
				urlbf.append(ActorKindCode.ADMIN.getName());
			}else if(ActorKindCode.LEADERS.getCode().equals(actorKindCode)){
				urlbf.append(ActorKindCode.LEADERS.getName());
			}else if(ActorKindCode.MEMBER.getCode().equals(actorKindCode)){
				urlbf.append(ActorKindCode.MEMBER.getName());
			}
			urlbf.append("/");
			urlbf.append(contentName);
			urlbf.append("/");
			urlbf.append(contentId);
			urlbf.append("\n");
		}
		String contentUrl = new String(urlbf);
		
		return contentUrl;
	}

}
