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

import tsuboneSystem.entity.TParty;

public class PartyResultAction extends tsuboneSystem.action.admin.PartyResultAction{
	
	@Override
	public String input() {
		TParty tParty = tPartyService.findById(partyForm.id);
		if (tParty == null) {
			return "/common/error.jsp";
		}else if(!tParty.creatorId.equals(loginMemberDto.memberId)){
			//対象の会議の制作者ではない
			if(tParty.resultEditMemberId != null){
				//すでに結果が入力されている場合
				if(tParty.resultEditEndFlag && !tParty.resultEditMemberId.equals(loginMemberDto.memberId)){
					//以後の編集が禁止になっていて、最後に編集した人ではないとき
					return "/common/error.jsp";
				}
			}
		}
		return super.input();
	}
}
