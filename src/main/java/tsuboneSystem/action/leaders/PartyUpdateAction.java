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

import javax.annotation.Resource;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.action.abstracts.PartyOperateAbstractAction;
import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyClub;

public class PartyUpdateAction extends PartyOperateAbstractAction{
	
	/** アクションネーム */
	public String actionName = "PartyUpdate";
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginLeadersDtoのDto */
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	boolean disabledFlag = true;
	
	@Execute(validator = false, reset = "resetInput", urlPattern = "{id}")
	public String input() {
		
		//２重登録防止の為Token作成
        TokenProcessor.getInstance().saveToken(request);

		TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
    	
        //登録されている部をすべてリストの形で呼び出す
        partyForm.clubList = tClubService.findAllOrderById();
        
        //マップを作る。形はkey(数値)とvalue(名称)の２個セットの形
        partyForm.clubMapSS = tClubService.getClubMap();
        
        int i = 0;
        partyForm.attendClub = new String[party.tPartyClubList.size()];
        for (TPartyClub tPartyClub : party.tPartyClubList) {
        	partyForm.attendClub[i] = tPartyClub.ClubId.toString();
        	i++;
        }
		
        return viewinput();
	}
	
    @Execute(validator = false)
	public String complete() {
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {

	    	TParty updateParty = new TParty(getLoginMemberId(), partyForm);
	    	//DBを更新する
	    	tPartyService.update(updateParty);
	    	
	    	//メールの送信があれば送信する
	        if (partyForm.mailSendFlag) {
	        	sendMail(partyForm, getLoginMemberId());
	        }
				return "partyComplete.jsp";
        } else {
        	return "/common/error.jsp";
        }
	}
    
	@Override
	protected Integer getLoginMemberId() {
		return loginLeadersDto.memberId;
	}
}
