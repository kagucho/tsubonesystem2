package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;

public class PartyUpdateAction extends tsuboneSystem.action.admin.PartyUpdateAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Resource
	public PartyDto partyDto;
	
	@Override
	public String input() {
		TParty party = tPartyService.findById(partyForm.id);
		if (!party.creatorId.equals(getLoginMemberId())) {
			return "/common/error.jsp";
		}else{
			partyDto.myPartyFlag = true;
		}
		return super.input();
	}
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
