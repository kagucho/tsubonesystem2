package tsuboneSystem.original.util;

import java.util.ArrayList;
import java.util.List;
import org.seasar.framework.container.SingletonS2Container;

import tsuboneSystem.code.ActorKindCode;
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
	
	//メールに記載する遷移先のId
	public Integer contentId = null;

	//タイトル
	public String title = null;
	
	//送信者のmemberID
	public Integer sendMemberID;
	
	//送信結果
	public boolean error = false;
	
	//送信結果を登録したTMail
	public TMail tMail = new TMail();
	
	
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
	 * メールに記載したいURL
	 * @param requestUrl
	 */
	public void setContentId(Integer contentId){
		this.contentId = contentId;
	}
	
	/**
	 * メール送信者のID
	 * @param sendMemberID
	 */
	public void setRegistId(Integer sendMemberID) {
		this.sendMemberID = sendMemberID;
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
			TsuboneSystemUtil mailUtil = new TsuboneSystemUtil();
			String actorKind = mailUtil.actorKind(tMember);
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
	public void sendMail(){
		
		MailManager mailManager = SingletonS2Container.getComponent(MailManager.class);
		mailManager.setTitle(title);
		mailManager.setContent(content);
		
		//管理者に対して
		if(!toAddressAdmin.isEmpty()){
			mailManager.setToAddress(toAddressAdmin.toArray(new TMember[0]));
			error = mailManager.sendMail();
		}
		//leadersに対して
		if(!toAddressLeaders.isEmpty()){
			mailManager.setToAddress(toAddressLeaders.toArray(new TMember[0]));
			error = mailManager.sendMail();
		}
		//一般メンバーに対して
		if(!toAddressInd.isEmpty()){
			mailManager.setToAddress(toAddressInd.toArray(new TMember[0]));
			error = mailManager.sendMail();
		}
		//ログを残す
		setLog(error);
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


}
