package tsuboneSystem.action.leaders;

import javax.annotation.Resource;
import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.entity.TParty;

public class PartyDeleteAction extends tsuboneSystem.action.admin.PartyDeleteAction{
	@Resource
	LoginLeadersDto loginLeadersDto;
	
	@Override
	public String input() {
		TParty party = tPartyService.findById(partyForm.id);
		if (party == null || party.meetingNecessaryFlag) {
			return "/common/error.jsp";
		}
		return super.input();
	}
	
	@Override
	public Integer getLoginMemberId() {
		return loginLeadersDto.memberId;
	}
}
