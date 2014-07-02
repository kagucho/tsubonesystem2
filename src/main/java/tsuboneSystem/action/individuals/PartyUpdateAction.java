package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TParty;

public class PartyUpdateAction extends tsuboneSystem.action.admin.PartyUpdateAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	public String input() {
		TParty party = tPartyService.findById(partyForm.id);
		if (!party.creatorId.equals(getLoginMemberId())) {
			return "/common/error.jsp";
		}
		return super.input();
	}
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
