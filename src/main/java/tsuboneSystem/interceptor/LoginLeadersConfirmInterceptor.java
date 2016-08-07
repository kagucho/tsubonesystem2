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
