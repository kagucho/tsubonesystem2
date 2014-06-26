package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;

public class PartyListAction extends tsuboneSystem.action.admin.PartyListAction{
	
	@Resource
	LoginIndividualsDto loginIndividualsDto;

	@Override
    protected Integer getLoginMemberId() {
    	return loginIndividualsDto.memberId;
    }
}
