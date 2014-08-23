package tsuboneSystem.action.leaders;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
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
	
	/** LoginAdminDto */
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
    @Execute(validator = false)
	public String index() {
    	
    	//ログインしているメンバー情報
    	myPageForm.tMember = loginLeadersDto.tMemberLogin;
    	actionNameSub = myPageForm.tMember.hname;
    	
    	//ログインしているメンバーの所属部一覧
    	myPageForm.tMemberClubList = tMemberClubService.findByMemberId(loginLeadersDto.tMemberLogin.id.toString());
    	myPageForm.tClubList = new ArrayList<TClub>();
    	for (TMemberClub tMemberClubOne : myPageForm.tMemberClubList) {
    		myPageForm.tClubList.add(tMemberClubOne.tClub);
    	}
    	
    	//現在時刻の取得と、その時点で出欠受付中かつ、まだ出欠を出していないの会議一覧
    	myPageForm.tPartyNoAttendList = new ArrayList<TParty>();
    	Date dateNow = new Date();
    	
    	//TODO 締切日がないものはどうするか？
    	myPageForm.tPartyNoAttendList = tPartyService.findNotAttendPartyByMemberId(new Date(), loginLeadersDto.memberId);
    	
    	//実行日に開催されている会議一覧
    	myPageForm.tPartyToDayList = tPartyService.findBy_MeetingDay_EQ_Now(dateNow);
    	
        return "index.jsp";
	}
}
