package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;

public class MemberListAction extends tsuboneSystem.action.admin.MemberListAction{
	
	
	/** LoginIndividualsDtoのDto */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
    @Override
    protected void setLoginUer() {
    	loginMember = loginIndividualsDto.tMemberLogin;
    }
}
