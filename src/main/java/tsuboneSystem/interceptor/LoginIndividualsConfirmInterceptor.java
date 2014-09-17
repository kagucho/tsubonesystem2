package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TMember;

public class LoginIndividualsConfirmInterceptor extends AbstractLoginInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	@Override
	protected boolean isLogined() {
		return (loginIndividualsDto != null && loginIndividualsDto.memberId != null);
	}

	@Override
	TMember getLoginTMember() {
		if (loginIndividualsDto == null) {
			return null;
		}
		return loginIndividualsDto.tMemberLogin;
	}

	@Override
	Integer getLoginMemberId() {
		if (loginIndividualsDto == null) {
			return null;
		}
		return loginIndividualsDto.memberId;
	}
	
	@Override
	protected void setRedirectUrl(String url) {
		loginIndividualsDto.redirectURL = url;
	}
}
