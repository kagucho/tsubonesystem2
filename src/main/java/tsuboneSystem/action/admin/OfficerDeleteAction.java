package tsuboneSystem.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.OfficerForm;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class OfficerDeleteAction {
	
	
	public String actionName = "ChiefUpdate";
	
	/** OfficerFormのフォーム */
	@ActionForm
	@Resource
	protected OfficerForm officerForm;
	
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
	
	//Leaders
	@Execute(validator = false, urlPattern = "{id}", reset="resetDeleteInput")
	public String index() {
		//渡ってきたidはtLeadersのidであるので、そこからTLeadersに登録されているレコードを検索し、編集対象レコードを特定しておく。
		officerForm.leadersOld = tLeadersService.findById(officerForm.id);
		officerForm.officerKind = officerForm.leadersOld.OfficerKind.toString();
		officerForm.tMemberNew = officerForm.leadersOld.tMember;
		officerForm.secretInformation = officerForm.leadersOld.secretInformation;
		officerForm.attendUpdate = officerForm.leadersOld.attendUpdate;
		officerForm.memberUpdate = officerForm.leadersOld.memberUpdate;
		return view();
	}
	//Admin
	@Execute(validator = false, urlPattern = "indexAdmin/{id}", reset = "resetDeleteInput")
	public String indexAdmin() {
		//渡ってきたidはtAdminのidであるので、そこからTAdminに登録されているレコードを検索し、編集対象レコードを特定しておく。
		officerForm.adminOld = tAdminService.findById(officerForm.id);
		officerForm.officerKind = officerForm.adminOld.OfficerKind.toString();
		officerForm.tMemberNew = officerForm.adminOld.tMember;
		return view();
	}
	
	//表示
	@Execute(validator = false)
	public String view(){
		return "officerDeleteConfirm.jsp";
	}
	
	//登録画面
	@Execute(validator = false)
	public String complete() {
		if (officerForm.officerKind.equals(LeadersKindCode.SUB_CHIEF.getCode()) || officerForm.officerKind.equals(LeadersKindCode.WEBADMIN.getCode())) {
			//削除(TAdmin)
			tAdminService.delete(officerForm.adminOld);
		} else {
			//削除(TLeaders)
			tLeadersService.delete(officerForm.leadersOld);
		}
		return "officerDeleteComplete.jsp";
	}
	
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
        return errors;
    }
}
