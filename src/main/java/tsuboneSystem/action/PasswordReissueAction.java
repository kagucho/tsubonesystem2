package tsuboneSystem.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.PasswordReissueForm;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TMemberService;

public class PasswordReissueAction {
	
	public String actionName = "PasswordReissue ";
	
	public String actionNameSub = "・・・・・・・パスワード忘れるんじゃねえ！！";
	
	/** LoginFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PasswordReissueForm passwordReissueForm;
	
	/** TMemberのサービス */
	@Binding
	protected TMemberService tMemberService;
	
    @Execute(validator = false)
	public String index() {
        return "passwordReissueInput.jsp";
	}
    
	@Execute(validator = true, input="login.jsp", validate="validateBase", stopOnValidationError = false)
	public String complete() {
		
		//パスワードはランダムで生成させる
    	passwordReissueForm.tempPass = RandomStringUtils.randomAlphabetic(8);
    	
    	//更新対象を検索する
    	TMember tMember = tMemberService.findByEmail(passwordReissueForm.mailAddress);
    	
    	//ランダムのパスワードを暗号化して更新する
    	if (tMember != null) {
    		tMember.password = DigestUtil.md5(passwordReissueForm.tempPass);;
    		tMemberService.update(tMember);
    		
    		//送信先
    		List<TMember>tMemberSendList = new ArrayList<TMember>();
        	tMemberSendList.add(tMember);
        	
        	//メールを送信する
        	String title = new String();
        	String content = new String();
        	
        	title = "仮パスワードが作成されました";
        	
        	StringBuffer buf = new StringBuffer();
        	buf.append("仮パスワードを発行しました。可及的速やかにパスワードを更新してください。");
        	buf.append("\n");
        	buf.append("\n");
        	buf.append("ID：");
        	buf.append(tMember.userName);
        	buf.append("\n");
        	buf.append("\n");
        	buf.append("仮Password：");
        	buf.append(passwordReissueForm.tempPass);
        	buf.append("\n");
        	buf.append("\n");
        	buf.append("あ、仮パスワードのまま使っているの見つけたら①号館から自由落下させます。あらかじめご了承ください。");
        	content = new String(buf);
        	
        	MailManagerUtil mailUtil = new MailManagerUtil();
        	mailUtil.setTitle(title);
        	mailUtil.setContent(content);	
        	mailUtil.setContentName("memberUpdate");
        	mailUtil.setContentId(tMember.id);
        	mailUtil.setToAddressActorSplit(tMemberSendList);
        	mailUtil.sendMail();
    	}
    	return "passwordReissueComplete.jsp";
	}
	//userNameの重複チェック
    public ActionMessages validateBase(){
    	ActionMessages errors = new ActionMessages();
    	return errors;
    }

}
