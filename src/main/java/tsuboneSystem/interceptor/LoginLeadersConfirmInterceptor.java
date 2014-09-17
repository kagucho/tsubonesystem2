package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.entity.TMember;

public class LoginLeadersConfirmInterceptor extends AbstractLoginInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	@Override
	TMember getLoginTMember() {
		if (loginLeadersDto == null) {
			return null;
		}
		return loginLeadersDto.tMemberLogin;
	}

	@Override
	Integer getLoginMemberId() {
		if (loginLeadersDto == null) {
			return null;
		}
		return loginLeadersDto.memberId;
	}

	@Override
	protected boolean isLogined() {
		return (loginLeadersDto != null && loginLeadersDto.memberId != null);
	}
	
	@Override
	protected void setRedirectUrl(String url) {
		loginLeadersDto.redirectURL = url;
	}
}
