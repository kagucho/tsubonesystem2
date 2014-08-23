package tsuboneSystem.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberDeleteAction {
	
	public String actionName = "MemberDelete";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String index() {
		
		TMember member = tMemberService.findById(memberForm.id);
		Beans.copy(member, memberForm).execute();
        
		//マップの取得
        memberForm.clubMap = tClubService.getClubMapIS();

        //パスワードは表示しない
        memberForm.password = "(パスワードは初期化のみ可能です)";
       
		
        return "memberConfirm.jsp";
	}
    
    @Execute(validator = false, validate="validateBase", input="memberConfirm.jsp", stopOnValidationError = false)
	public String complete() {
    	
    	TMember member = tMemberService.findById(memberForm.id);
    	member.deleteFlag = true;
    	tMemberService.update(member);
    	
    return "memberComplete.jsp";
    }
    
  //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
    	
    	//選択されたMemberが現役の部長以上の役職に付いている場合、連絡先をすべて登録しているかを確認する。
    	List<TLeaders> tLeadersList = tLeadersService.findByMemberIdList(memberForm.id);
    	if (tLeadersList.size() > 0) {
    		for (TLeaders tLeadersOne : tLeadersList) {
    			TClub tClub = tClubService.findByLeadersId(tLeadersOne.id);
    			if (tClub != null) {
    				//各部の現役の部長の場合
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため削除できません",false));
    			}else if (tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.GASSYUKU.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.RIDAISAI.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ETC.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))) {
    				//局長もしくは副局長の場合
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため削除できません",false));
    			}
    		}
    	}	
    	
    	//管理者の情報は編集できない
    	List<TAdmin> tAdminList = tAdminService.findByMemberIdList(memberForm.id);
    	if(tAdminList.size() > 0){
    		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため削除できません",false));
    	}
    	
        return errors;
    }
}
    
