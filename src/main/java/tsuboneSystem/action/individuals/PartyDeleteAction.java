package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TParty;

public class PartyDeleteAction extends tsuboneSystem.action.admin.PartyDeleteAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	public String input() {
		TParty party = tPartyService.findById(partyForm.id);
		if (party == null || !party.creatorId.equals(getLoginMemberId())) {
			return "/common/error.jsp";
		}
		return super.input();
	}
	
	@Override
	public Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
