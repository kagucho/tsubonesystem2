package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;


public class MessageDeleteAjaxAction extends tsuboneSystem.action.admin.MessageDeleteAjaxAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
