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

package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;


public class PartyDetailAction extends tsuboneSystem.action.admin.PartyDetailAction {
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Resource
	public LoginMemberDto loginMemberDto;
	
	//サブメッセージ
	public String actionNameSub = null;
	
	//自分が主催の会議ならTRUE
	public boolean myPartyFlag = false;
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
	
	@Override
	protected TMember getLoginTMember() {
		return loginIndividualsDto.tMemberLogin;
	}
	
	@Execute(validator = false, urlPattern = "detail/{id}", reset = "resetInput")
	public String index() {
		String nextPage = super.index();
		
		//もし自分が主催の会議ならフラグをTRUEにする
		if (partyForm.creatorId.equals(getLoginMemberId())) {
			myPartyFlag = true;
			partyDto.myPartyFlag = myPartyFlag;
			actionNameSub = "この会議の作成者はあなたです。";
		}else{
			myPartyFlag = false;
			partyDto.myPartyFlag = myPartyFlag;
			actionNameSub = null;
		}
		
		return nextPage;
	}
}
