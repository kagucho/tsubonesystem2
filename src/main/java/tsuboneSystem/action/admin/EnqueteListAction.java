/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
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

	public String actionName = "ClubList";

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
    	enqueteListForm.list = tEnqueteService.findAllJoinTable(loginMemberDto.memberId);
    	tEnqueteSelectService.findById(id)

        return "enqueteList.jsp";
	}
}
