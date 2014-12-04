package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.code.SexCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberApproveAction {
	
	public String actionName = "MemberDetail";
	
	/** Memberフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	@Execute(validator = false, urlPattern = "approve/{id}")
	public String approve(){
		
		//承認
		memberForm.approveFlag = true;
		
		return index();
	}
	
	@Execute(validator = false, urlPattern = "disApprove/{id}")
	public String disApprove(){
		
		//非承認
		memberForm.approveFlag = false;
		
		return index();
	}

	@SuppressWarnings("boxing")
	@Execute(validator = false)
	public String index() {

		memberForm.clubMap = tClubService.getClubMapIS();
		
		// IDから対象メンバーを特定する
		TMember memberApprove = new TMember();
		memberApprove = tMemberService.findById(memberForm.id);
		// 検索した結果をformにコピー
		Beans.copy(memberApprove, memberForm).execute();	
		
		memberForm.sexMap = new HashMap<String, String>();
		for (Integer i=1; i<=3; i++) {
			memberForm.sexMap.put(i.toString(), SexCode.getnameByCode(i.toString()));
		}
		// Idから対象のメンバーが所属している部の一覧を検索する
		memberForm.tMemberClubList = tMemberClubService.findByMemberId(memberForm.id.toString());
		
		//パスワードは表示しない
		memberForm.password = "(パスワードは初期化のみ可能です)";
		return viewinput();
	}
	
	@Execute(validator = false)
	public String viewinput(){
		return "memberApproveConfirm.jsp";
	}
	
	@Execute(validator = false)
	public String complete() {
		//DB更新
		TMember tMember = tMemberService.findById(memberForm.id);
		
		if(memberForm.approveFlag){
			//仮登録フラグを倒す
			tMember.tempMemberFlag = false;
		}else{
			//仮登録フラグを倒さず、削除する
			tMember.tempMemberFlag = true;
			tMember.deleteFlag = true;
		}
		tMemberService.update(tMember);
		
		if(memberForm.approveFlag){
			//メール送信
			String title;
			String content;
			
			//タイトル
			title = "メンバーの登録が承認されました";
			//内容
			StringBuffer buff = new StringBuffer();
			buff.append("メンバーの登録が完了されましたのでお知らせします。");
			buff.append("\n");
			buff.append("登録者：");
			buff.append(memberForm.hname);
			buff.append("(");
			buff.append(memberForm.name);
			buff.append(")");
			buff.append("\n");
			buff.append("\n");
			buff.append("メンバーの仮登録が承認されました。以後は設定したパスワードでログインできるようになります。");
			buff.append("\n");
			content = new String(buff);
			
			//送信相手(承認した人・本人)
			List<TMember> tMemberSendList = new ArrayList<TMember>();
			tMemberSendList.add(loginMemberDto.tMemberLogin);// 承認した人
			tMemberSendList.add(tMember);//本人
			
			//メールを送信する
			MailManagerUtil mailUtil = new MailManagerUtil();
			mailUtil.setRegistId(loginMemberDto.memberId);
			mailUtil.setBrowsingRights(MailBrowsingRightsCode.ADMIN.getCodeNumber());
			mailUtil.setTitle(title);
			mailUtil.setContent(content);	
			mailUtil.setLinkUrlFlag(false);
			mailUtil.setToAddressActorSplit(tMemberSendList);
			mailUtil.sendMail();
		}
		return "memberApproveComplete.jsp";
	}
}
