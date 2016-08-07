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

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.action.abstracts.PartyOperateAbstractAction;
import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;

public class PartyRegistAction extends PartyOperateAbstractAction{
	
	/** アクションネーム */
	public String actionName = "PartyRegist";
	
	/** LoginAdminDto */
	@Resource
	public LoginAdminDto loginAdminDto;
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		
		/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        partyForm.mailSendFlag = false;
        partyForm.disabledFlag = false;
        
        //keyをclubId, valueをclubNameとしてマップを作成する
        partyForm.clubMapSS = tClubService.getClubMap();
         
        return viewinput();
	}
    
	@Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	//会議情報をpartyテーブルに追加
        	TParty party = tPartyService.insertCustom(loginMemberDto.memberId, partyForm);
        	
        	//完了画面から詳細画面遷移のためにIDを取得
        	partyForm.id = party.id;
        	
        	//対象にobを含めるかどうか
        	boolean containsOb = StringUtil.isNotEmpty(partyForm.ObAttendFlag);
        	
        	//参加対象な部が選択されていたらTPartyClubにレコードを挿入する
        	if (partyForm.attendClub != null) {
        		//PartyClubテーブルにレコードを挿入する
        		insertTPartyClub(party.id);
        	}
        	
        	//PartyAttendテーブルに未回答として対象者を登録する
        	insertPartyAttend(getTMemberForDb(containsOb), party.id);
        	
        	//メール送信の必要があれば送信する
        	if (partyForm.mailSendFlag) {
        		sendMail(partyForm, getLoginMemberId());
        	}
	        return "partyComplete.jsp";	
        } else {
        	return "/common/error.jsp";
        }
	}

	protected Integer getLoginMemberId() {
		return loginAdminDto.memberId;
	}

	/**
	 * 対象のパーティに参加対象のTMemberを取得する
	 * @param containsOb
	 * @return
	 */
	protected List<TMember> getTMemberForDb(boolean containsOb) {
		if (partyForm.attendClub != null) {
			return tMemberService.findByClubIdsForParty(containsOb, partyForm.attendClub);
		} else {
			return tMemberService.findAllOrderById(containsOb);
		}
	}

	/**
	 * PartyAttendに未回答として登録する
	 * @param tMemberList
	 * @param partyId
	 */
	protected void insertPartyAttend(Collection<TMember> tMemberList, Integer partyId) {
		for (TMember tMember : tMemberList) {
    		TPartyAttend tPartyAttend = new TPartyAttend();
    		tPartyAttend.memberId = tMember.id;
    		tPartyAttend.partyId = partyId;
    		tPartyAttend.attend = Integer.valueOf(PartyAttendCode.UNSUBMITTED.getCode());
    		tPartyAttendService.insert(tPartyAttend);
    	}
	}
}
