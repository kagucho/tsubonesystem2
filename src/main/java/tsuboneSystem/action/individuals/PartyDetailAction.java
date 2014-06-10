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
package tsuboneSystem.action.individuals;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TPartyService;

public class PartyDetailAction {
	
	public String actionName = "PartyDetail";
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
    	Beans.copy(party, partyDto).execute();
    	
    	//現在時刻を取得し、期限内か判断する
    	Date dateNow = new Date();
    	partyForm.deadFlag = partyDto.deadFlag(party, dateNow);
    	
        return "partyDetail.jsp";
	}
}
