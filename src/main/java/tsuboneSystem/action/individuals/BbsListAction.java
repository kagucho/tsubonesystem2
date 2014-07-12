package tsuboneSystem.action.individuals;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.AttendForm;
import tsuboneSystem.form.BbsForm;
import tsuboneSystem.service.TBbsSubjectService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyService;

public class BbsListAction {
	
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
	
	/** 会議の一覧 */
	public List<TParty> partyItem;
	
    
    @Execute(validator = false,reset = "resetInput")
	public String index() {
    	
    	//一覧を取得
    	bbsForm.tBbsSubjectList = tBbsSubjectService.findOrderByUpdateTime();
    	
    	return "BbsList.jsp";
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "BbsList.jsp";
    }
    
    //新しいスレッド作成
    @Execute(validator = true,input = "viewinput()")
	public String bbsRegist() {
    	
    	//DBに書き込む
    	TBbsSubject tBbsSubject = new TBbsSubject();
    	tBbsSubject.title = bbsForm.title;
    	tBbsSubject.memberId = loginIndividualsDto.memberId;
    	tBbsSubjectService.insert(tBbsSubject);  
    		
    	return "/individuals/bbsList/?redirect=true";
	}
    
}
