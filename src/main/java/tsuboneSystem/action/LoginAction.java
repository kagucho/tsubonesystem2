package tsuboneSystem.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.LoginForm;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberService;

public class LoginAction {
	
	public String actionName = "TsuboneSystem";
	
	public String actionNameSub = "TsuboneSystemは出欠席管理、メンバー管理、簡易メーリス、非常時連絡先参照を目的に作られたシステムです。";
	
	/** LoginFormのアクションフォーム */
	@ActionForm
	@Resource
	protected LoginForm loginForm;
	
	/** TMemberのサービス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TLeadersServiceのサービス */
	@Resource
	protected TLeadersService tLeadersService;
	
	/** TClubServiceのサービス */
	@Resource
	protected TClubService tClubService;
	
	/**admin用ののDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** Indivifuals用のDto */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	
    @Execute(validator = false)
	public String index() {
        return "login.jsp";
	}
    
	@Execute(validator = true, input="login.jsp", validate="validateBase", stopOnValidationError = false)
	public String complete() {
    	
		String id = new String();
		String password = new String();
		
		id = loginForm.id;
		password = DigestUtil.md5(loginForm.password);
		
		//パスワードとIDで検索する
    	TMember member = tMemberService.findByLoginIdPassword(id, password);
    	if (member != null ) {
    		
    		//メンバーに該当がいたら、そのメンバーが役職に付いているかを判定する
    		List<TLeaders> leaderList = tLeadersService.findByMemberIdList(member.id);

        	//部長以上の場合はadmin
        	if (leaderList.size() > 0) {
        		loginAdminDto.memberId = member.id;
        		loginAdminDto.tMemberLogin = member;
        		loginAdminDto.tLeadersList = leaderList;
        		return "/admin/?redirect=true";
        	}else{
        		//通常部員はIndividuals
        		loginIndividualsDto.memberId = member.id;
        		loginIndividualsDto.tMemberLogin = member;
        		//メール不達フラグが立っていたらメンバー更新画面に飛ばす
            	if (loginIndividualsDto.tMemberLogin.sendErrorFlag) {
            		return "/individuals/memberUpdate/input/?redirect=true";
            	}
           		return "/individuals/?redirect=true";
        	}
        	
    	} 
    	//Memberに該当がない場合はログイン画面に戻す
        return "/login/?redirect=true";
	}
	//userNameの重複チェック
    public ActionMessages validateBase(){
        ActionMessages errors = new ActionMessages();
        
        String id = new String();
		String password = new String();
		
		id = loginForm.id;
		password = DigestUtil.md5(loginForm.password);
		
        TMember tMember = tMemberService.findByLoginIdPassword(id, password);
		if (tMember == null) {
			errors.add("signError",new ActionMessage("残念！！IDもしくはPassが違います。",false));
		}
        return errors;
    }
}
