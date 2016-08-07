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

package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;

public class LogoutAction {
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;

	
	@Execute(validator = false)
	@RemoveSession(name = {"loginLeadersDto", "loginMemberDto"})
	public String index() {
        return "/login/?redirect=true";
	}
}
