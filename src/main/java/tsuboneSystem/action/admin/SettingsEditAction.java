package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TTempLogin;
import tsuboneSystem.form.SettingsEditForm;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TTempLoginService;

public class SettingsEditAction {
	
	public String actionName = "Settings";
	
	/** アクションフォーム */
	@ActionForm
	@Resource
	public SettingsEditForm settingsEditForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** TTempLoginServiceのサービスクラス */
	@Resource
	protected TTempLoginService tTempLoginService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	
	/**--------------- 一時メンバー編集 -----------**/
	/** 入力画面 **/
    @Execute(validator = false)
	public String tempMemberInput() {
	
    	return viewinput();
	}
    
    @Execute(validator = false)
    public String viewinput(){
    	
    	settingsEditForm.tTempLoginNow = tTempLoginService.findAll();
    	
    	return "tempMemberInput.jsp";
    }
    
    /** 確認画面 **/
    @Execute(validator = true, validate="validateBase", input="viewinput", stopOnValidationError = false)
   	public String tempMemberConfirm() {
    	
    	//パスワードはランダムで生成させる
    	String rdn = RandomStringUtils.randomAlphabetic(8);
    	settingsEditForm.password = rdn;
    	
    	return "tempMemberConfirm.jsp";
   	}
    
    /** 完了画面 **/
    @Execute(validator = false)
   	public String tempMemberComplete() {
    	
    	//古い仮登録情報は削除する
    	List<TTempLogin> tTempLoginsListOld = tTempLoginService.findAll();
    	for(TTempLogin one : tTempLoginsListOld){
    		tTempLoginService.delete(one);
    	}
    	
    	//新しい情報を入れる
    	TTempLogin tTempLogin = new TTempLogin();
    	tTempLogin.userName = settingsEditForm.userName;
    	tTempLogin.password = DigestUtil.md5(settingsEditForm.password);
    	tTempLoginService.insert(tTempLogin);
    	
    	//生成されたときはweb管理者にメールが飛ぶ
    	List<TAdmin> tAdminList = tAdminService.findByKind(LeadersKindCode.WEBADMIN.getCode());
    	List<TMember> tMemberSendList = new ArrayList<TMember>();
    	for(TAdmin tAdmin : tAdminList){
    		tMemberSendList.add(tAdmin.tMember);
    	}
    	
    	//メールのタイトルと内容を作成する
    	String title = new String();
    	String content = new String();
    	
    	title = "仮IDと仮パスワードが作成されました";
    	
    	StringBuffer buf = new StringBuffer();
    	buf.append("仮IDと仮パスワードが作成されたので連絡します。取り扱いには十分留意してください。");
    	buf.append("\n");
    	buf.append("制作者：");
    	buf.append(loginMemberDto.tMemberLogin.hname);
    	buf.append("\n");
    	buf.append("\n");
    	buf.append("仮ID：");
    	buf.append(tTempLogin.userName);
    	buf.append("\n");
    	buf.append("仮Password：");
    	buf.append(settingsEditForm.password);
    	buf.append("\n");
    	buf.append("\n");
    	content = new String(buf);
    	
    	//メールを送信する
    	
    	MailManagerUtil mailUtil = new MailManagerUtil();
    	mailUtil.setTitle(title);
    	mailUtil.setContent(content);	
    	mailUtil.setLinkUrlFlag(false);
    	mailUtil.setToAddressActorSplit(tMemberSendList);
    	mailUtil.sendMail();
    	
    	return "tempMemberComplete.jsp";
   	}
    
    /**--------------- 一時メンバー削除 -----------**/
    /** 確認画面 **/
    @Execute(validator = false)
   	public String tempMemberDeleteConfirm() {
    	return "tempMemberDeleteConfirm.jsp";
    }
    
    /** 完了画面 **/
    @Execute(validator = false)
   	public String tempMemberDeleteComplete() {
    	
    	//セキュリティのためすべてのデータを削除する
    	List<TTempLogin> tTempLoginList = tTempLoginService.findAll();
    	if(tTempLoginList.size() > 0){
    		for(TTempLogin one : tTempLoginList){
        		tTempLoginService.delete(one);
        	}
    	}
    	
    	return "tempMemberDeleteComplete.jsp";
    }
    
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
        // userNameの重複チェック
        TMember tMember = tMemberService.findByUserName(settingsEditForm.userName);	
        TTempLogin tTempLogin = tTempLoginService.findByUserName(settingsEditForm.userName);
		if (tMember != null || tTempLogin != null) {
			errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
		}
		
        return errors;
    }
}
