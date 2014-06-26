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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.form.MyPageForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class IndexAction {
	
	public String actionName = "Welcome!!";
	
	/** Indexのアクションフォーム */
	@ActionForm
	@Resource
	protected MyPageForm myPageForm;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyServiceのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	/** LoginIndividualsDto */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
    @Execute(validator = false)
	public String index() {
    	
    	//ログインしているメンバー情報
    	myPageForm.tMember = loginIndividualsDto.tMemberLogin;
    	
    	//ログインしているメンバーの所属部一覧
    	myPageForm.tMemberClubList = tMemberClubService.findByMemberId(loginIndividualsDto.tMemberLogin.id.toString());
    	myPageForm.tClubList = new ArrayList<TClub>();
    	for (TMemberClub tMemberClubOne : myPageForm.tMemberClubList) {
    		myPageForm.tClubList.add(tMemberClubOne.tClub);
    	}
    	
    	//現在時刻の取得と、その時点で出欠受付中かつ、まだ出欠を出していないの会議一覧
    	myPageForm.tPartyNoAttendList = new ArrayList<TParty>();
    	Date dateNow = new Date();
    	  	
    	//出席対象が部で絞られている場合の会議一覧
    	Set<TParty> partySet = new  HashSet<TParty>();
    	List<TMemberClub> tMemberClubList = tMemberClubService.findByMemberId(loginIndividualsDto.tMemberLogin.id.toString());
    	for (TMemberClub tMemberClubOne : tMemberClubList) {
    		List<TPartyClub> tPartyClubList = tPartyClubService.findByClubIdPartyGE(tMemberClubOne.ClubId,dateNow);
    		for (TPartyClub tPartyClubOne : tPartyClubList) {
    			partySet.add(tPartyClubOne.tParty);
    		}
    	}
    	List<TParty> tPartyListYesClub = new ArrayList<TParty>(partySet);
    	
    	//出席対象が部で絞られていない場合の会議一覧
    	List<TParty> tPartyList = tPartyService.findBy_Deadline_GE_Now(dateNow);
    	List<TParty> tPartyListNoClub = new ArrayList<TParty>();
    	for (TParty tPartyOne : tPartyList) {
    		if (tPartyOne.tPartyClubList.size() == 0) {
    			tPartyListNoClub.add(tPartyOne);
    			}
    	}
    	
    	myPageForm.tPartyList = new ArrayList<TParty>();
    	myPageForm.tPartyList.addAll(tPartyListYesClub);
    	myPageForm.tPartyList.addAll(tPartyListNoClub);
    	
    	if(myPageForm.tPartyList.size() != 0){
    		for (TParty tParty : myPageForm.tPartyList){
        		TPartyAttend tPartyAttend = tPartyAttendService.findByPartyIdMemberId(tParty.id,loginIndividualsDto.memberId);
        		if(tPartyAttend != null){
        			//会議が作られた後に加入したメンバーには出欠レコードがない
        			if (PartyAttendCode.UNSUBMITTED.getCode().equals(tPartyAttend.attend.toString())){
            			//会議に対する出欠席ステータスが未提出だった場合のみ追加
            			myPageForm.tPartyNoAttendList.add(tParty);
            		}
        		}
        	}
    	}
    	
    	//実行日に開催されている会議一覧
    	myPageForm.tPartyToDayList = tPartyService.findBy_MeetingDay_EQ_Now(dateNow);
    	
        return "index.jsp";
	}
}
