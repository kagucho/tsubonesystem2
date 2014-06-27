package tsuboneSystem.action.individuals;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.ClubForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class ClubDetailAction {
	
	public String actionName = "ClubDetail";
	
	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected ClubForm clubForm;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	TClub club = tClubService.findById(clubForm.id);
    	Beans.copy(club, clubForm).execute();
    	clubForm.tMember = club.tLeaders.tMember;
    	
    	//部に所属している人一覧用のリストの初期化
    	clubForm.tMemberList = new ArrayList<TMember>();
    	
    	//選択されている部のIDでtMemberClubを検索し、取得したリストからメンバーの情報をリストにaddしていく
    	clubForm.tMemberClubList = tMemberClubService.findByIdFortMember(clubForm.id);
    	for (TMemberClub memberClubOne : clubForm.tMemberClubList) {
    		clubForm.tMemberList.add(memberClubOne.tMember);
    	}
    	
        return "clubDetail.jsp";
	}
}
