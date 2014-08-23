package tsuboneSystem.action.leaders;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.ClubForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class ClubUpdateAction {
	
	
	public String actionName = "ClubUpdate";
	
	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected ClubForm clubForm;
	
	/** Member用のDto */
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
	
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	@Execute(validator = false, urlPattern = "{id}")
	public String index() {
		
		TClub tClub = tClubService.findById(clubForm.id);
		Beans.copy(tClub, clubForm).execute();
		
		//部長になり得るメンバーのマップを作成する
		clubForm.tMemberAllList = new ArrayList<TMember>();
		clubForm.memberMap = new HashMap<String,String>();
		clubForm.tMemberClubList = tMemberClubService.findByClubIdInMember(clubForm.id.toString());
		for (TMemberClub memberClubOne : clubForm.tMemberClubList) {
			clubForm.tMemberAllList.add(memberClubOne.tMember);
		}
		for (TMember memberOne : clubForm.tMemberAllList) {
			clubForm.memberMap.put(memberOne.id.toString(), memberOne.hname);	
		}
		
			
				
        return viewinput();
	}
	
	//confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "clubInput.jsp";
    }
    
    @Execute(validator = true, validate="validateBase", input="clubInput.jsp", stopOnValidationError = false)
	public String confirm() {
        return "clubConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	TClub clubUp = new TClub();
    	Beans.copy(clubForm, clubUp).execute();
    	TLeaders tLeaders = tClubService.findById(clubForm.id).tLeaders;
    	tLeaders.MemberId = Integer.valueOf(clubForm.OfficerId);
    	
    	tLeadersService.update(tLeaders);
    	
    	clubUp.LeadersId = tLeaders.id;
    	tClubService.update(clubUp);
    	
        return "clubComplete.jsp";
	}
    
  //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
    	
    	//選択されたMemberが連絡先をすべて登録しているかを確認する。
    	TMember tMember = tMemberService.findById(Integer.valueOf(clubForm.OfficerId));
    	if (tMember.mail.isEmpty() || tMember.tel1.isEmpty() || tMember.tel2.isEmpty() || tMember.tel3.isEmpty()) {
    		errors.add("OfficerId",new ActionMessage("このメンバーには連絡先のどれかが登録されていません。メール、電話番号をすべて登録するか他のメンバーを選択してください。",false));
    	}
    	
        return errors;
    }
}
