package tsuboneSystem.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.ActorKindCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.dto.LoginTempLoginDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TTempLogin;
import tsuboneSystem.form.LoginForm;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TTempLoginService;

public class LoginAction {
	
	public String actionName = "TsuboneSystem";
	
	public String actionNameSub = "TsuboneSystemは出欠席管理、メンバー管理、簡易メーリス、非常時連絡先参照を目的に作られたシステムです。";
	
	/** LoginFormのアクションフォーム */
	@ActionForm
	@Resource
	protected LoginForm loginForm;
	
	/** TMemberのサービス */
	@Binding
	protected TMemberService tMemberService;
	
	/** TAdminServiceのサービス */
	@Binding
	protected TAdminService tAdminService;
	
	/** TLeadersServiceのサービス */
	@Binding
	protected TLeadersService tLeadersService;
	
	/** TTempLoginServiceのサービス */
	@Binding
	protected TTempLoginService tTempLoginService;
	
	/** TClubServiceのサービス */
	@Binding
	protected TClubService tClubService;
	
	/**admin用ののDto */
	@Binding
	public LoginAdminDto loginAdminDto;
	
	/** Individuals用のDto */
	@Binding
	public LoginIndividualsDto loginIndividualsDto;
	
	/** Leaders用のDto */
	@Binding
	public LoginLeadersDto loginLeadersDto;
	
	/** LoginTempLoginDto用のDto */
	@Binding
	public LoginTempLoginDto loginTempLoginDto;
	
	/** Member用のDto */
	@Binding
	public LoginMemberDto loginMemberDto;
	
	/** 遷移先 */
	public String url = null;
	
	/** 遷移先(取得時) */
	public String redirectURL = null;

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
    	
    	//遷移先を取得
    	if(StringUtil.isNotEmpty(loginAdminDto.redirectURL)){
    		redirectURL = loginAdminDto.redirectURL;
    	}else if(StringUtil.isNotEmpty(loginLeadersDto.redirectURL)){
    		redirectURL = loginLeadersDto.redirectURL;
    	}else if(StringUtil.isNotEmpty(loginIndividualsDto.redirectURL)){
    		redirectURL = loginIndividualsDto.redirectURL;
    	}else{
    		redirectURL = null;
    	}
    	
    	//仮登録用のメンバーであったとき
    	TTempLogin tTempLogin = tTempLoginService.findByLoginIdPassword(id, password);
    	if(tTempLogin != null){
    		loginTempLoginDto.memberId = tTempLogin.id;
    		loginTempLoginDto.tMemberLogin = tTempLogin;
    		loginMemberDto.actorKindCode = ActorKindCode.TEMP.getCodeNumber();
    		loginMemberDto.actorKind = ActorKindCode.TEMP.getName();
    		return "/tempRegist/?redirect=true";
    	}
    	
    	//パスワードとIDで検索する
    	TMember member = tMemberService.findByLoginIdPassword(id, password);
    	if (member != null ) {
    		
    		//メンバーに該当がいたら、そのメンバーが役職に付いているかを判定する
    		List<TLeaders> leaderList = new ArrayList<TLeaders>();
    		leaderList = tLeadersService.findByMemberIdOrderKindList(member.id);
    		
    		//メンバーに該当がいたら、そのメンバーが管理者に付いているかを判定する
    		TAdmin tAdmin = tAdminService.findByMemberId(member.id);
    		
    		//管理者の場合はadmin
    		if(tAdmin != null){
    			loginAdminDto.memberId = member.id;
        		loginAdminDto.tMemberLogin = member;
        		loginAdminDto.tLeadersList = leaderList;
        		loginMemberDto.memberId = member.id;
        		loginMemberDto.tMemberLogin = member;
        		loginMemberDto.actorKindCode = ActorKindCode.ADMIN.getCodeNumber();
        		loginMemberDto.actorKind = ActorKindCode.ADMIN.getName();
        		
        		//遷移先が格納されている時はそちらに飛ぶ
        		if(StringUtil.isNotEmpty(redirectURL)){
        			url = urlCreator(redirectURL,ActorKindCode.ADMIN.getCode());
        			loginAdminDto.redirectURL = null;
        			return url;
        		}
        		return "/admin/?redirect=true";
    		}
    		
        	//部長以上の場合はleaders
        	if (leaderList.size() > 0) {
    			loginLeadersDto.memberId = member.id;
    			loginLeadersDto.tMemberLogin = member;
    			loginLeadersDto.tLeadersList = leaderList;
    			
    			//二つ以上の役職についている場合の権限は、trueを優先する
    			loginLeadersDto.secretInformation = false;
    			loginLeadersDto.memberUpdate = false;
    			loginLeadersDto.attendUpdate= false;
    			for(TLeaders tLeadersOne : loginLeadersDto.tLeadersList){
    				if(tLeadersOne.secretInformation){
    					loginLeadersDto.secretInformation = true;
    				}
    				if(tLeadersOne.memberUpdate){
    					loginLeadersDto.memberUpdate = true;
    				}
    				if(tLeadersOne.attendUpdate){
    					loginLeadersDto.attendUpdate= true;
    				}
    			}
            	loginMemberDto.memberId = member.id;
            	loginMemberDto.tMemberLogin = member;
            	loginMemberDto.actorKindCode = ActorKindCode.LEADERS.getCodeNumber();
            	loginMemberDto.actorKind = ActorKindCode.LEADERS.getName();
            	//遷移先が格納されている時はそちらに飛ぶ
        		if(StringUtil.isNotEmpty(redirectURL)){
        			url = urlCreator(redirectURL,ActorKindCode.LEADERS.getCode());
        			return url;
        		}
            	return "/leaders/?redirect=true";
        	}else{
        		//通常部員はIndividuals
        		loginIndividualsDto.memberId = member.id;
        		loginIndividualsDto.tMemberLogin = member;
        		loginMemberDto.memberId = member.id;
        		loginMemberDto.tMemberLogin = member;
        		loginMemberDto.actorKindCode = ActorKindCode.MEMBER.getCodeNumber();
        		loginMemberDto.actorKind = ActorKindCode.MEMBER.getName();
        		//メール不達フラグが立っていたらメンバー更新画面に飛ばす
        		if (loginIndividualsDto.tMemberLogin.sendErrorFlag) {
        		return "/individuals/memberUpdate/input/?redirect=true";
        		}
        		//CSV登録で登録されたメンバーだったら更新画面に飛ばす
        		if(loginIndividualsDto.tMemberLogin.name == null && loginIndividualsDto.tMemberLogin.sex == null){
        		return "/individuals/memberUpdate/input/?redirect=true";
        		}
        		//遷移先が格納されている時はそちらに飛ぶ
        		if(StringUtil.isNotEmpty(redirectURL)){
        			url = urlCreator(redirectURL,ActorKindCode.MEMBER.getCode());
        			return url;
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
    	
    	//検索
    	TMember tMember = tMemberService.findByLoginIdPassword(id, password);
    	TTempLogin tTempLogin = tTempLoginService.findByLoginIdPassword(id, password);
    	
    	//検索に該当がなかった場合
    	if (tMember == null && tTempLogin == null) {
    		errors.add("signError",new ActionMessage("残念！！IDもしくはPassが違います。",false));
    	}else if (tMember != null && tMember.tempMemberFlag){
    		errors.add("signError",new ActionMessage("このメンバーはまだ仮登録状態です。",false));
    	}
    	return errors;
    }
    
    public String urlCreator(String warUrl, String actorKind){

//    	String[] splUrl;
//    	String lastUrl = null;
//    	
//    	splUrl = warUrl.split("/",3);
//    	lastUrl =splUrl[2];
//    	
//    	StringBuffer bf = new StringBuffer();
//    	if(actorKind.equals(ActorKindCode.ADMIN.getCode())){
//    		bf.append("/admin/");
//    	}else if(actorKind.equals(ActorKindCode.LEADERS.getCode())){
//    		bf.append("/leaders/");
//    	}else{
//    		bf.append("/individuals/");
//    	}
//    	bf.append(lastUrl);
//    	String url = new String(bf);
    	
    	StringBuffer bf = new StringBuffer();
    	bf.append(warUrl);
    	bf.append("/?redirect=true");
    	
    	return bf.toString();
    }
}
