package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TParty;

public class PartyResultAction extends tsuboneSystem.action.admin.PartyResultAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	public String input() {
		TParty tParty = tPartyService.findById(partyForm.id);
		if (tParty == null || !tParty.creatorId.equals(getLoginMemberId())) {
			return "/common/error.jsp";
		}
		return super.input();
	}
	
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
