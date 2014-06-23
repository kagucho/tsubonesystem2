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
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class PartyResultAction {
	
	
	public String actionName = "PartyResult";
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
	/** LoginAdminDtoのDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	
	@Execute(validator = false, urlPattern = "{id}")
	public String input() {
        return "partyInput.jsp";
	}
    
    @Execute(validator = true, input = "partyInput.jsp")
	public String confirm() {
        return "partyConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	//更新
    	TParty tParty = tPartyService.findById(partyForm.id);
    	tParty.meetingResult = partyForm.meetingResult;
    	tPartyService.update(tParty);
    	
        return "partyComplete.jsp";
	}
}
