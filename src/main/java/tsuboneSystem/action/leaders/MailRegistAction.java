package tsuboneSystem.action.leaders;

import javax.annotation.Resource;
import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.entity.TMember;

public class MailRegistAction extends tsuboneSystem.action.admin.MailRegistAction {

	/** LoginAdminDto */
	@Resource
	protected LoginLeadersDto loginLeadersDto;

	protected Integer getLoginMemberId() {
		return loginLeadersDto.memberId;
	}
	
	protected TMember getLoginTMember() {
		return loginLeadersDto.tMemberLogin;
	}
}
