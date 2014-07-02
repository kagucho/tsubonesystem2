
package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;

public class PartyRegistAction extends tsuboneSystem.action.admin.PartyRegistAction{
	
	@Resource
	LoginIndividualsDto loginIndividualsDto;

	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
