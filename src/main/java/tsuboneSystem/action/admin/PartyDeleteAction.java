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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TPartyService;

public class PartyDeleteAction {
	
	
	public String actionName = "PartyDelete";
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
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
		
		TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
		
        return "partyConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	TParty partyDelete = new TParty();
    	Beans.copy(partyForm, partyDelete).excludes("meetingDay","meetingTime","meetingDeadlineDay","meetingDeadlineTime").execute();
    	//日付と日時をString型からDate型に変換
    	try {
    		if(partyForm.meetingDay != null){
    			Date meetingDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDay.toString()).getTime());
    			Date meetingTime = new Date(new SimpleDateFormat("HH:mm").parse(partyForm.meetingTime.toString()).getTime());
    			partyDelete.meetingDay = meetingDay;
    			partyDelete.meetingTime = meetingTime;
    		}
			if(partyForm.meetingDeadlineDay != null){
				Date meetingDeadlineDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDeadlineDay.toString()).getTime());
				partyDelete.meetingDeadlineDay = meetingDeadlineDay;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	partyDelete.deleteFlag = Boolean.valueOf(true);
    	tPartyService.update(partyDelete);
    	
        return "partyComplete.jsp";
	}
}
