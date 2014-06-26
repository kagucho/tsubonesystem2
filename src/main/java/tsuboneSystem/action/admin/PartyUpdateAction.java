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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.entity.TPartySendMail;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartySendMailService;
import tsuboneSystem.service.TPartyService;

public class PartyUpdateAction {
	
	/** アクションネーム */
	public String actionName = "PartyUpdate";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** tMailServiceのサービスクラス */
	@Resource
	protected TPartySendMailService tPartySendMailService;
	
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
	
	boolean disabledFlag = true;
	
	@Execute(validator = false, reset = "resetInput", urlPattern = "{id}")
	public String input() {
		
		TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
    	
    	/** 詳細画面にて部の表示のためにmapを作成する　**/
        //登録されている部をすべてリストの形で呼び出す
        partyForm.clubList = tClubService.findAllOrderById();
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        partyForm.clubMapSS = new HashMap<String,String>();
        
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : partyForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	partyForm.clubMapSS.put(club.id.toString(), club.ClubName);
        }
        
        int i = 0;
        partyForm.attendClub = new String[party.tPartyClubList.size()];
        for (TPartyClub tPartyClubOne : party.tPartyClubList) {
        	partyForm.attendClub[i] = tPartyClubOne.ClubId.toString();
        	i++;
        }
		
        return "partyInput.jsp";
	}
    
    @Execute(validator = true, input = "partyInput.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
	public String confirm() {
    	if (partyForm.mailSendFlag) {
    		
    		//OBを除いた全員
    		if (partyForm.mailSendAllFlag != null && partyForm.mailSendOBFlag == null) {
    			partyForm.tMemberSendList = tMemberService.findByIdNoOBAll();	
    		//OBを含めた全員
    		}else if (partyForm.mailSendAllFlag != null && partyForm.mailSendOBFlag != null) {
    			partyForm.tMemberSendList = tMemberService.findAllOrderById();	
    		//部で指定されていた場合
    		}else if (partyForm.clubListCheck != null) {
    			partyForm.MemberSendSet = new HashSet<Integer>();
    			//選択された部をひとつ取り、それらに紐づくメンバーIDをsetに入れる
    			for (String cLubIDOne : partyForm.clubListCheck) {
    				List<TMemberClub> tMemberClubList = new ArrayList<TMemberClub>();
    				tMemberClubList = tMemberClubService.findByClubId(cLubIDOne);
    				for (TMemberClub tMemberClubOne : tMemberClubList) {
    					if (partyForm.mailSendOBFlag == null) {
    						if (!tMemberClubOne.tMember.obFlag) {
        						partyForm.MemberSendSet.add(tMemberClubOne.MemberId);
        					}	
    					}else{
    						partyForm.MemberSendSet.add(tMemberClubOne.MemberId);
    					}
    				}
    			}
    			partyForm.tMemberSendList = new ArrayList<TMember>();
    			for (Integer one : partyForm.MemberSendSet) {
    				partyForm.tMemberSendList.add(tMemberService.findById(one));
    			}
    		}	
    		return "partyConfirm.jsp";
    	}else{
    		return "partyConfirm.jsp";
    	}   
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	TParty UpParty = new TParty();
    	Beans.copy(partyForm, UpParty).excludes("meetingDay","meetingTime","meetingDeadlineDay","meetingDeadlineTime").execute();
    	
    	//編集者の情報
    	UpParty.creatorId = loginAdminDto.memberId;
    	
    	//日付と日時をString型からDate型に変換
    	try {
			Date meetingDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDay.toString()).getTime());
			Date meetingTime = new Date(new SimpleDateFormat("HH:mm").parse(partyForm.meetingTime.toString()).getTime());
			Date meetingDeadlineDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDeadlineDay.toString()).getTime());
			
			UpParty.meetingDay = meetingDay;
			UpParty.meetingTime = meetingTime;
			UpParty.meetingDeadlineDay = meetingDeadlineDay;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	//DBに書き込む
    	tPartyService.update(UpParty);
    	
    	List<TPartyClub> tPartyClubLsit = tPartyClubService.findByPartyId(partyForm.id);
    	if (tPartyClubLsit.size() > 0) {
    		for (TPartyClub tPartyClubOne : tPartyClubLsit) {
    			tPartyClubService.delete(tPartyClubOne);
    		}
    	}
    	if (partyForm.attendClub != null ) {
    		//出席対象者に指定があった場合
    		for (String clubId : partyForm.attendClub) {
    			//partyIdとclubIdをセットで格納する
    			TPartyClub tPartyClub = new TPartyClub();
    			tPartyClub.PartyId = UpParty.id;
    			tPartyClub.ClubId = Integer.valueOf(clubId);
    			tPartyClubService.insert(tPartyClub);
    		}
    	}
    	//メールの送信がない場合はそのまま完了画面に遷移
        if (!partyForm.mailSendFlag) {
        	return "partyComplete.jsp";	
        }else {
			return sendMail();
		}
	}
    
  //以下メールの配信処理
    @Execute(validator = false)
	public String sendMail() {
    	
    	//メールの送信者のID
    	partyForm.registMemberId = loginAdminDto.memberId;
    	
    	//TMailにメールの内容を追加する
    	TMail tMail = new TMail();
    	Beans.copy(partyForm, tMail).execute();
   
    	//メールを送信する
    	MailManager manager = new MailManager();
    	manager.setTitle(partyForm.title);
    	manager.setContent(partyForm.content);
    	manager.setToAddress(partyForm.tMemberSendList.toArray(new TMember[0]));
    	if (manager.sendMail()) {
    		mailMsg = "メールを正常に送信しました。";
    		tMail.errorFlag = false;
    	} else {
    		mailMsg = "メールの送信に失敗しました。";
    		tMail.errorFlag = true;
    	}   
    	
    	//メールの内容を登録する
    	tMailService.insert(tMail);

    	//TMailSendAttendにメールの送信相手を追加する
    	TMailSendMember tMailSendMember = new TMailSendMember();
    	for (TMember tMemberOne : partyForm.tMemberSendList) {
    		tMailSendMember.mailId = tMail.id;
    		tMailSendMember.memberId = tMemberOne.id;
    		tMailSendMemberService.insert(tMailSendMember);
    	}
    	
    	//会議とメールを紐付けるDBに登録する
    	TPartySendMail tPartySendMail = new TPartySendMail();
    	tPartySendMail.PartyId = partyForm.id;
    	tPartySendMail.MailId = tMail.id;
    	tPartySendMailService.insert(tPartySendMail);
    	
    return "partyComplete.jsp";	
    }
}
