package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.MyPageForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class IndexAction {
	
	public String actionName = "Welcome!!";
	
	public String actionNameSub = null;
	
	/** Indexのアクションフォーム */
	@ActionForm
	@Resource
	protected MyPageForm myPageForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDto */
	@Resource
	public LoginAdminDto loginAdminDto;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyServiceのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	
    @Execute(validator = false)
	public String index() {
    	
    	//ログインしているメンバー情報
    	myPageForm.tMember = loginMemberDto.tMemberLogin;
    	
    	//ログインしているメンバーの所属部一覧
    	myPageForm.tMemberClubList = tMemberClubService.findByMemberId(loginMemberDto.tMemberLogin.id.toString());
    	myPageForm.tClubList = new ArrayList<TClub>();
    	for (TMemberClub tMemberClubOne : myPageForm.tMemberClubList) {
    		myPageForm.tClubList.add(tMemberClubOne.tClub);
    	}
    	
    	//TODO 締切日がないものはどうするか？
    	myPageForm.tPartyNoAttendList = tPartyService.findNotAttendPartyByMemberId(new Date(), loginMemberDto.memberId);
    	
    	return "index.jsp";
	}
}
