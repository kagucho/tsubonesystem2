package tsuboneSystem.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.OfficerForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class OfficerUpdateAction {
	
	
	public String actionName = "ChiefUpdate";
	
	
	/** OfficerFormのフォーム */
	@ActionForm
	@Resource
	protected OfficerForm officerForm;
	
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
	
	//選択画面
	@Execute(validator = false, urlPattern = "{id}")
	public String index() {
		
		//Memberの選択肢一覧取得
		officerForm.memberItems = tMemberService.findByIdNoOBAll();
		
		//渡ってきたidはtLeadersのidであるので、そこからTLeadersに登録されているレコードを検索し、編集対象レコードを特定しておく。
		officerForm.leadersUp = tLeadersService.findById(officerForm.id);
		
        return "officerInput.jsp";
	}
    
	//確認画面
    @Execute(validator = true, urlPattern = "confirm/{id}", validate="validateBase", input="officerInput.jsp", stopOnValidationError = false)
	public String confirm() {
    	
    		officerForm.tMemberNew = tMemberService.findById(officerForm.id); 	
    		
        return "officerConfirm.jsp";
	}
    
    //登録画面
    @Execute(validator = false)
	public String complete() {

    	officerForm.leadersUp.MemberId = officerForm.tMemberNew.id;
    	tLeadersService.update(officerForm.leadersUp);
    	
    	
        return "officerComplete.jsp";
	}
    
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
		//選択されたid(memberId)でTLeaders(memberId)を検索し該当がなく、局長にも副局長にもなっていないことを確認する。
    	List<TLeaders> leadersOld = tLeadersService.findByMemberIdList(officerForm.id.toString());
    	if (leadersOld.size() > 0) {
    		for (TLeaders tLeaderOne : leadersOld){
    			if (tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.CHIEF.getCode())) || tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.SUB_CHIEF.getCode())) || tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))){
    				errors.add("leadersOld",new ActionMessage("このメンバーはすでに局長、もしくは副局長のになっているメンバーです。",false));
    			}
    		}
    	}
    	
    	//選択されたMemberが連絡先をすべて登録しているかを確認する。
    	TMember tMember = tMemberService.findById(officerForm.id);
    	if (tMember.mail.isEmpty() || tMember.tel1.isEmpty() || tMember.tel2.isEmpty() || tMember.tel3.isEmpty()) {
    		errors.add("leadersConnection",new ActionMessage("このメンバーには連絡先のどれかが登録されていません。メール、電話番号をすべて登録するか他のメンバーを選択してください。",false));
    	}
    	
        return errors;
    }
}
