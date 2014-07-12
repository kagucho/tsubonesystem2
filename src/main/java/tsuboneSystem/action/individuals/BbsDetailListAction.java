package tsuboneSystem.action.individuals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TBbsDetail;
import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.AttendForm;
import tsuboneSystem.form.BbsForm;
import tsuboneSystem.service.TBbsDetailService;
import tsuboneSystem.service.TBbsSubjectService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyService;

public class BbsDetailListAction {
	
	public String actionName = "Kagucho.BBS";
	
	/** BbsFormのアクションフォーム */
	@ActionForm
	@Resource
	protected BbsForm bbsForm;
	
	/** LoginIndividualsDtoのサービスクラス */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TBbsSubjectServiceのサービスクラス */
	@Resource
	protected TBbsSubjectService tBbsSubjectService;
	
	/** TBbsDetailServiceのサービスクラス */
	@Resource
	protected TBbsDetailService tBbsDetailService;
	
	//選択されたスレ
	public TBbsSubject tBbsSubject;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	tBbsSubject = null;
    	bbsForm.tBbsDetailList = new ArrayList<TBbsDetail>();
    	
    	tBbsSubject = tBbsSubjectService.findById(bbsForm.id); 
    	bbsForm.tBbsDetailList = tBbsDetailService.findBySubjectId(bbsForm.id);
    			
    	return "BbsDetailList.jsp";
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "BbsDetailList.jsp";
    }
    
    @Execute(validator = true,input = "viewinput")
	public String bbsDetailRegist() {
    	
    	//DBに書き込み
    	TBbsDetail tBbsDetail = new TBbsDetail();
    	tBbsDetail.subjectId = bbsForm.id;
    	tBbsDetail.detail = bbsForm.detail;
    	tBbsDetail.memberId = loginIndividualsDto.memberId;
    	tBbsDetailService.insert(tBbsDetail);
    	bbsForm.detail = null;
    	
    return "/individuals/bbsDetailList/?redirect=true";
	}
    
}
