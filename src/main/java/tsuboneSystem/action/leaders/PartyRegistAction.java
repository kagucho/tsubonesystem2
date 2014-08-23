
package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginLeadersDto;

public class PartyRegistAction extends tsuboneSystem.action.admin.PartyRegistAction{
	
	@Resource
	LoginLeadersDto loginLeadersDto;

	protected Integer getLoginMemberId() {
		return loginLeadersDto.memberId;
	}
}
