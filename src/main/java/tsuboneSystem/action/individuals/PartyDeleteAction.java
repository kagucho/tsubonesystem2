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

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TParty;

public class PartyDeleteAction extends tsuboneSystem.action.admin.PartyDeleteAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	public String input() {
		TParty party = tPartyService.findById(partyForm.id);
		if (party == null || !party.creatorId.equals(getLoginMemberId())) {
			return "/common/error.jsp";
		}
		return super.input();
	}
	
	@Override
	public Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
}
