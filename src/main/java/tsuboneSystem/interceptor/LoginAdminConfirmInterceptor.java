package tsuboneSystem.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMember;

public class LoginAdminConfirmInterceptor extends AbstractLoginInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	@Resource
	public HttpServletRequest httpServletRequest;
	
	@Resource
	public HttpSession session;
	
	
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


	@Override
	protected void setRedirectUrl(String url) {
		loginAdminDto.redirectURL = url;
	}
}
