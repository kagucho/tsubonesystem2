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

package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.EnqueteListForm;
import tsuboneSystem.service.TEnqueteSelectService;
import tsuboneSystem.service.TEnqueteService;

public class EnqueteListAction {

	public String actionName = "EnqueteList";

	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected EnqueteListForm enqueteListForm;

	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMemberのサービスクラス */
	@Resource
	protected TEnqueteService tEnqueteService;

	/** TMemberのサービスクラス */
	@Resource
	protected TEnqueteSelectService tEnqueteSelectService;

    @Execute(validator = false)
	public String index() {
    	enqueteListForm.list = tEnqueteService.findAllOrderById(loginMemberDto.memberId);

    	return "enqueteList.jsp";
	}
}
