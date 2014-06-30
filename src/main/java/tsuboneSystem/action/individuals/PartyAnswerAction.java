package tsuboneSystem.action.individuals;

import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAnswerCode;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.form.PartyQuestionAnswerForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TPartyAnswerService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyQuestionService;
import tsuboneSystem.service.TPartyService;

public class PartyAnswerAction {
	
	public String actionName = "PartyDetail";
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService  tPartyClubService;
	
	/** TPartyAnswerServiceのサービスクラス */
	@Resource
	protected TPartyAnswerService  tPartyAnswerService;
	
	/** TPartyQuestionServiceのサービスクラス */
	@Resource
	protected TPartyQuestionService  tPartyQuestionService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** PartyQuestionAnswerFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyQuestionAnswerForm partyQuestionAnswerForm;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** LoginIndividualsDto */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	/** 送信エラーフラグ */
	public boolean errorFlag;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{partyId}/{questionId}", reset = "resetInput")
	public String index() {
    	
    	partyQuestionAnswerForm.tPartyQuestion = tPartyQuestionService.findById(partyQuestionAnswerForm.questionId);
    	
    	//メール送信対象のマップ
    	partyQuestionAnswerForm.answerSendKindMap = new HashMap<String, String>();
        for (Integer i=0; i<=2; i++) {
        	partyQuestionAnswerForm.answerSendKindMap.put(i.toString(), PartyAnswerCode.getnameByCode(i.toString()));
        }
    	
        return viewinput();
	}
    
  //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyAnswerList.jsp";
    }
}
