package tsuboneSystem.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.OfficerForm;
import tsuboneSystem.service.TAdminService;
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
	
	//選択画面
	@Execute(validator = false, urlPattern = "{id}")
	public String index() {
		
		//Memberの選択肢一覧取得
		officerForm.memberItems = tMemberService.findAllOrderById(false);
		//渡ってきたidはtLeadersのidであるので、そこからTLeadersに登録されているレコードを検索し、編集対象レコードを特定しておく。
		officerForm.leadersOld = tLeadersService.findById(officerForm.id);
		officerForm.secretInformation = officerForm.leadersOld.secretInformation;
		officerForm.attendUpdate = officerForm.leadersOld.attendUpdate;
		officerForm.clubUpdate = officerForm.leadersOld.clubUpdate;
		officerForm.memberUpdate = officerForm.leadersOld.memberUpdate;
		
        return "officerInput.jsp";
	}
    
	//権限選択画面
    @Execute(validator = true, urlPattern = "confirm/{id}", validate="validateBase", input="officerInput.jsp", stopOnValidationError = false)
	public String confirm() {
    	
    		officerForm.tMemberNew = tMemberService.findById(officerForm.id); 	
    		
        return "officerConfirm.jsp";
	}
    
    //確認画面(第2)
    @Execute(validator = true, input="officerInput.jsp", reset = "resetInput")
	public String confirmSec() {
    		
        return "officerConfirmSec.jsp";
	}
    
    //登録画面
    @Execute(validator = false)
	public String complete() {

    	officerForm.leadersOld.MemberId = officerForm.tMemberNew.id;
    	if(officerForm.memberUpdate){
    		//メンバーの更新権限があれば、秘匿情報は公開される。
    		officerForm.leadersOld.secretInformation = true;
    	}else{
    		officerForm.leadersOld.secretInformation = officerForm.secretInformation;
    	}
    	officerForm.leadersOld.memberUpdate = officerForm.memberUpdate;
    	officerForm.leadersOld.attendUpdate = officerForm.attendUpdate;
    	officerForm.leadersOld.clubUpdate = officerForm.clubUpdate;
    	
    	tLeadersService.update(officerForm.leadersOld);
    	
        return "officerComplete.jsp";
	}
    
    /*------------------------------------------------以下管理者変更用メソッド----------------------------------------------------------*/
    //選択画面
  	@Execute(validator = false, urlPattern = "indexAdmin/{id}", reset = "resetInput")
  	public String indexAdmin() {
  		
  		//Memberの選択肢一覧取得
  		officerForm.memberItems = tMemberService.findAllOrderById(false);
  		//渡ってきたidはtLeadersのidであるので、そこからTLeadersに登録されているレコードを検索し、編集対象レコードを特定しておく。
  		officerForm.adminOld = tAdminService.findById(officerForm.id);
  		
          return indexAdminView();
  	}
  	
  	@Execute(validator = false)
  	public String indexAdminView(){
  		return "officerAdminInput.jsp";
  	}
      
  	//確認画面
      @Execute(validator = true, urlPattern = "confirmAdmin/{id}", validate="validateBase", input="indexAdminView", stopOnValidationError = false)
  	public String confirmAdmin() {
      	
      		officerForm.tMemberNew = tMemberService.findById(officerForm.id); 	
      		
          return "officerAdminConfirm.jsp";
  	}
      
      
      //登録画面
      @Execute(validator = false)
  	public String completeAdmin() {

      	officerForm.adminOld.MemberId = officerForm.tMemberNew.id;
      	tAdminService.update(officerForm.adminOld);
      	
          return "officerComplete.jsp";
  	}
      
      /*------------------------------------------------以下部長権限変更メソッド----------------------------------------------------------*/
      //選択画面
    	@Execute(validator = false, reset = "resetInput")
    	public String clubChiefInput() {
    		
    		List<TLeaders> tleaLeadersListOld = tLeadersService.findByKind(LeadersKindCode.DIRECTOR.getCode());
    		for(TLeaders tLeadersOne : tleaLeadersListOld){
    			if(tLeadersOne.secretInformation){
    				officerForm.secretInformation = true;
    			}
    			if(tLeadersOne.memberUpdate){
    				officerForm.memberUpdate = true;
    			}
    			if(tLeadersOne.clubUpdate){
    				officerForm.clubUpdate = true;
    			}
    			if(tLeadersOne.attendUpdate){
    				officerForm.attendUpdate = true;
    			}
    		}
    		
            return "clubChiefInput.jsp";
    	}
        
    	//確認画面
        @Execute(validator = false, stopOnValidationError = false, reset = "resetInput")
    	public String clubChiefConfirm() {
            return "clubChiefConfirm.jsp";
    	}
        
        //登録画面
        @Execute(validator = false)
    	public String clubChiefComplete() {
        	
        	List<TLeaders> tLeadersList = tLeadersService.findByKind(LeadersKindCode.DIRECTOR.getCode());
        	for(TLeaders tLeadersOne : tLeadersList){
        		tLeadersOne.clubUpdate = officerForm.clubUpdate;
        		tLeadersOne.attendUpdate = officerForm.attendUpdate;
        		tLeadersOne.memberUpdate = officerForm.memberUpdate;
        		if(officerForm.memberUpdate){
        			//メンバーの更新権限があれば、秘匿情報は公開される。
        			tLeadersOne.secretInformation = true;
        		}else{
        			tLeadersOne.secretInformation = officerForm.secretInformation;
        		}
        		tLeadersService.update(tLeadersOne);
        	}
        	
            return "clubChiefComplete.jsp";
    	}
    
    
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
		//選択されたid(memberId)でTLeaders(memberId)を検索し該当がなく、局長にも副局長にもなっていないことを確認する。
    	List<TLeaders> leadersOld = tLeadersService.findByMemberIdList(officerForm.id);
    	if (leadersOld.size() > 0) {
    		for (TLeaders tLeaderOne : leadersOld){
    			if (tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.CHIEF.getCode())) || tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.SUB_CHIEF.getCode())) || tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))){
    				errors.add("leadersOld",new ActionMessage("このメンバーはすでに局長、もしくは副局長のになっているメンバーです。",false));
    			}
    		}
    	}
    	
    	//選択されたMemberが連絡先をすべて登録しているかを確認する。
    	TMember tMember = tMemberService.findById(officerForm.id);
    	if (StringUtils.isEmpty(tMember.mail) || StringUtils.isEmpty(tMember.tel1) || StringUtils.isEmpty(tMember.tel2) || StringUtils.isEmpty(tMember.tel3)) {
    		errors.add("leadersConnection",new ActionMessage("このメンバーには連絡先のどれかが登録されていません。メール、電話番号をすべて登録するか他のメンバーを選択してください。",false));
    	}
    	
        return errors;
    }
}
