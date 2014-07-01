package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TMember;

public class MailRegistAction extends tsuboneSystem.action.admin.MailRegistAction{
	
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
	
	@Override
	protected TMember getLoginTMember() {
		return loginIndividualsDto.tMemberLogin;
	}
}
