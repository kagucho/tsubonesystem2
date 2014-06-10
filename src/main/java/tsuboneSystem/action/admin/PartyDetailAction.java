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

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class PartyDetailAction {
	
	public String actionName = "PartyDetail";
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService  tPartyClubService;
	
	/** PartyFormのアクションフォーム */
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
    	
    	 int i = 0;
         partyForm.attendClub = new String[party.tPartyClubList.size()];
         partyDto.attendClub = new String[party.tPartyClubList.size()];
         for (TPartyClub tPartyClubOne : party.tPartyClubList) {
         	partyForm.attendClub[i] = tPartyClubOne.ClubId.toString();
         	partyDto.attendClub[i] = tPartyClubOne.ClubId.toString();
         	i++;
         }
    	
    	//現在時刻を取得し、期限内か判断する
    	Date dateNow = new Date();
    	partyForm.deadFlag = partyDto.deadFlag(party, dateNow);
    	
    	/** 詳細画面にて部の表示のためにmapを作成する　**/
        //登録されている部をすべてリストの形で呼び出す
        partyForm.clubList = tClubService.findAllOrderById();
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        partyForm.clubMapIS = new HashMap<Integer,String>();
        
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : partyForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	partyForm.clubMapIS.put(club.id, club.ClubName);
        }
    	
        return "partyDetail.jsp";
	}
}
