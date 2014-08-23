package tsuboneSystem.action.leaders;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TBbsDetail;
import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.form.BbsForm;
import tsuboneSystem.service.TBbsDetailService;
import tsuboneSystem.service.TBbsSubjectService;
import tsuboneSystem.service.TMemberService;

public class BbsDetailListAction {
	
	public String actionName = "Kagucho.BBS";
	
	/** BbsFormのアクションフォーム */
	@ActionForm
	@Resource
	protected BbsForm bbsForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
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
    	tBbsDetail.memberId = loginMemberDto.memberId;
    	tBbsDetailService.insert(tBbsDetail);
    	bbsForm.detail = null;
    	
    return "/leaders/bbsDetailList/?redirect=true";
	}
    
}
