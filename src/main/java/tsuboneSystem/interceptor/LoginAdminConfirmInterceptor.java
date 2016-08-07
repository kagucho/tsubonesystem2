/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tsuboneSystem.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.annotation.tiger.Binding;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMember;

public class LoginAdminConfirmInterceptor extends AbstractLoginInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Binding
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
