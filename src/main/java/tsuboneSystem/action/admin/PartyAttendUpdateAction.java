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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.PartyAttendForm;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyService;

public class PartyAttendUpdateAction {
	
	public String actionName = "PartyAttendUpdate";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyAttendForm partyAttendForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** 会議の一覧 */
	public List<TParty> partyItem;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}")
    @RemoveSession(name="partyAttendForm")
	public String on() {
    	switch (partyAttendForm.id) {
		case 1:
			partyAttendForm.tMemberOn = partyDto.tMemberOn;
			partyAttendForm.btn1 = "欠席に変更する";
			partyAttendForm.btn1Key = "1";
			partyAttendForm.btn2 = "未回答に変更する";
			partyAttendForm.btn2Key = "2";
			break;
		case 2:
			partyAttendForm.tMemberOff = partyDto.tMemberOff;
			partyAttendForm.btn1 = "出席に変更する";
			partyAttendForm.btn1Key = "3";
			partyAttendForm.btn2 = "未回答に変更する";
			partyAttendForm.btn2Key = "2";
			break;
		case 3:
			partyAttendForm.tMemberKuzu = partyDto.tMemberKuzu;
			partyAttendForm.btn1 = "出席に変更する";
			partyAttendForm.btn1Key = "3";
			partyAttendForm.btn2 = "欠席に変更する";
			partyAttendForm.btn2Key = "1";
			break;
		}
    	
        return "partyAttendUpdateInput.jsp";
	}	
    
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "confirm/{id}")
	public String confirm() {
    	
    	partyAttendForm.tPartyAttendNew = new ArrayList<TPartyAttend>();
    	partyAttendForm.tMemberNew = new ArrayList<TMember>();
    	switch (partyAttendForm.id) {
    	//現状から欠席に移行する
		case 1:
			for (String offMemberId : partyAttendForm.offCheck) {
				TPartyAttend tPartyAttendNew = tPartyAttendService.findByMemberIdWithPartyId(offMemberId, partyDto.id);
				tPartyAttendNew.attend = Integer.valueOf(PartyAttendCode.NO_ATTEND.getCode());
				partyAttendForm.tPartyAttendNew.add(tPartyAttendNew);
				partyAttendForm.tMemberNew.add(tMemberService.findById(Integer.valueOf(offMemberId)));
			}	
			break;
		//現状から未回答に移行する
		case 2:
			for (String noAttendMemberIdOne : partyAttendForm.offCheck){
				TPartyAttend tPartyAttendNew = tPartyAttendService.findByMemberIdWithPartyId(noAttendMemberIdOne, partyDto.id);
				tPartyAttendNew.attend = Integer.valueOf(PartyAttendCode.UNSUBMITTED.getCode());
				partyAttendForm.tPartyAttendNew.add(tPartyAttendNew);
				partyAttendForm.tMemberNew.add(tMemberService.findById(Integer.valueOf(noAttendMemberIdOne)));
			}
			
			break;
		//現状から出席に移行する
		case 3:
			for (String onMemberId : partyAttendForm.offCheck) {
				TPartyAttend tPartyAttendNew = tPartyAttendService.findByMemberIdWithPartyId(onMemberId, partyDto.id);
				tPartyAttendNew.attend = Integer.valueOf(PartyAttendCode.YES_ATTEND.getCode());
				partyAttendForm.tPartyAttendNew.add(tPartyAttendNew);
				partyAttendForm.tMemberNew.add(tMemberService.findById(Integer.valueOf(onMemberId)));
			}
			break;
		}	
    	return "partyAttendUpdateConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	if (partyAttendForm.tPartyAttendNew.size() > 0) {
    		for (TPartyAttend tPartyAttendNewOne : partyAttendForm.tPartyAttendNew) {
    				tPartyAttendService.update(tPartyAttendNewOne);
    		} 
    	}
        return "partyAttendUpdateComplete.jsp";
	}
    
}
