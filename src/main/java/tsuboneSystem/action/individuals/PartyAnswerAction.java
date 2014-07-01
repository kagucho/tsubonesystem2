package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TMember;

public class PartyAnswerAction extends tsuboneSystem.action.admin.PartyAnswerAction{
	/** LoginIndividualsDto */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
	
	@Override
	protected TMember getLoginTMember() {
		return loginIndividualsDto.tMemberLogin;
	}
}
