package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMember;

public class LoginAdminConfirmInterceptor extends AbstractLoginInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	@Override
	TMember getLoginTMember() {
		if (loginAdminDto == null) {
			return null;
		}
		return loginAdminDto.tMemberLogin;
	}


	@Override
	Integer getLoginMemberId() {
		if (loginAdminDto == null) {
			return null;
		}
		return loginAdminDto.memberId;
	}

	@Override
	protected boolean isLogined() {
		return (loginAdminDto != null && loginAdminDto.memberId != null);
	}
}
