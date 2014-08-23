package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TPartyService;

public class PartyResultAction {
	
	public String actionName = "PartyResult";
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDtoのDto */
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	@Execute(validator = false, urlPattern = "{id}")
	public String input() {
        return viewinput();
	}
	
    @Execute(validator = false)
	public String viewinput() {
    	return "partyInput.jsp";
    }
    
    @Execute(validator = true, input = "partyInput.jsp")
	public String confirm() {
        return "partyConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	//更新
    	TParty tParty = tPartyService.findById(partyForm.id);
    	tParty.meetingResult = partyForm.meetingResult;
    	tPartyService.update(tParty);
    	
        return "partyComplete.jsp";
	}
   
    protected Integer getLoginMemberId() {
    	return loginLeadersDto.memberId;
    }
}
