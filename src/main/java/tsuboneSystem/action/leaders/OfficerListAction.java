package tsuboneSystem.action.leaders;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.aop.annotation.RemoveSession;
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

public class OfficerListAction {
	
	public String actionName = "OfficerList";
	
	/** ClubFormのフォーム */
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
	
	
    @Execute(validator = false)
    @RemoveSession(name="officerForm")
	public String index() {
    	
    	//局長
    	officerForm.tLeadersChief = tAdminService.findByKind(LeadersKindCode.CHIEF.getCode());
    	
    	//副局長
    	officerForm.tLeadersSubChief = tAdminService.findByKind(LeadersKindCode.SUB_CHIEF.getCode());
    	
    	//会計
    	officerForm.tLeadersAccount = tLeadersService.findByKind(LeadersKindCode.ACCOUNT.getCode());
	
    	//以下、各部長の一覧
    	officerForm.officerListItem = tClubService.findAllInTmember();
    	
    	//合宿委員
    	officerForm.tLeadersGassyuku = tLeadersService.findByKind(LeadersKindCode.GASSYUKU.getCode());
    	
    	//web管理者
    	officerForm.tLeadersWebAdmin = tAdminService.findByKind(LeadersKindCode.WEBADMIN.getCode());
	
        return "OfficerList.jsp";
	}
}
