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

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TEnquete;
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.form.EnqueteForm;
import tsuboneSystem.service.TEnqueteSelectService;
import tsuboneSystem.service.TEnqueteService;

public class EnqueteRegistAction {
	
	/** アクションネーム */
	public String actionName = "EnqueteRegist";

	/** アクションフォーム */
	@ActionForm
	@Resource
	public EnqueteForm enqueteForm;

	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** LoginAdminDto */
	@Resource
	public LoginAdminDto loginAdminDto;

	/** サービス */
	@Resource
	public TEnqueteService tEnqueteService;

	/** 選択肢サービス */
	@Resource
	public TEnqueteSelectService tEnqueteSelectService;

	@Execute(validator = false, reset = "resetInput")
	public String index() {
		return viewinput();
	}

	@Execute(validator = false, reset = "resetInput")
	public String viewinput() {
		return "enqueteInput.jsp";
	}

	@Execute(validator = true, input = "viewinput", reset = "resetInput", validate = "validateBase", stopOnValidationError = false)
	public String confirm() {
		return "enqueteConfirm.jsp";
	}

	@Execute(validator = false)
	public String complete() {
		//DBに新規登録
		TEnquete tEnquete = new TEnquete();
		Beans.copy(enqueteForm, tEnquete).execute();
		tEnquete.createId = loginMemberDto.memberId;
		tEnquete.memberId = loginMemberDto.memberId;
		tEnqueteService.insert(tEnquete);
		enqueteForm.id = tEnquete.id;

		//選択肢を入れる
		for (String selectedContents : enqueteForm.selectedContents) {

			TEnqueteSelect tEnqueteSelect = new TEnqueteSelect();
			tEnqueteSelect.enqueteId = tEnquete.id;
			tEnqueteSelect.selectedContents = selectedContents;
			tEnqueteSelectService.insert(tEnqueteSelect);

		}

		return "enqueteComplete.jsp";
	}
}
