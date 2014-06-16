/*
x * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.ClubForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class ClubDetailAction {
	
	/** アクションネーム */
	public String actionName = "ClubDetail";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected ClubForm clubForm;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** ログインユーザ*/
	@Resource
	protected LoginAdminDto loginAdminDto;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	//部の詳細を取得する
    	TClub club = tClubService.findById(clubForm.id);
    	Beans.copy(club, clubForm).execute();
    	clubForm.tMember = club.tLeaders.tMember;
    	
    	//部に所属している人一覧用のリストの初期化
    	clubForm.tMemberList = new ArrayList<TMember>();
    	
    	//選択されている部のIDでtMemberClubを検索し、取得したリストからメンバーの情報をリストにaddしていく
    	clubForm.tMemberClubList = tMemberClubService.findByIdFortMember(clubForm.id);
    	for (TMemberClub memberClubOne : clubForm.tMemberClubList) {
    		clubForm.tMemberList.add(memberClubOne.tMember);
    	}
    	
    return "clubDetail.jsp";
	}
    
    /** メール配信　*/
    //入力画面
    @Execute(validator = false)
    public String contentRegist(){	
    	// 2重送信防止のためのTokenの生成
        TokenProcessor.getInstance().saveToken(request);
    return "clubMailRegist.jsp";	
    }
 
    //確認画面
    @Execute(validator = false)
    public String confirm(){
    	//部の所属する人にメールを送る
    	clubForm.tMemberSendList = clubForm.tMemberList;
    return "clubMailConfirm.jsp";	
    }
    
    //完了画面
    @Execute(validator = false)
    public String complete(){
	
    	// 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	clubForm.registMemberId = loginAdminDto.memberId;
        	
        	//TMailにメールの内容を追加する
        	TMail tMail = new TMail();
        	Beans.copy(clubForm, tMail).execute();
        	
        	//TMailSendAttendにメールの送信相手を追加する
        	for (TMember tMemberOne : clubForm.tMemberSendList) {
        		TMailSendMember tMailSendMember = new TMailSendMember();
        		tMailSendMember.mailId = tMail.id;
        		tMailSendMember.memberId = tMemberOne.id;
        		tMailSendMemberService.insert(tMailSendMember);
        	}
        	
        	//メールを送信する
        	MailManager manager = new MailManager();
        	manager.setTitle(clubForm.title);
        	manager.setContent(clubForm.content);
        	manager.setToAddress(clubForm.tMemberSendList.toArray(new TMember[0]));
        	if (manager.sendMail()) {
        		mailMsg = "メールを正常に送信しました。";
        		tMail.errorFlag = false;
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        		tMail.errorFlag = true;
        	}	
        	tMailService.insert(tMail);
        }  
    return "clubMailComplete.jsp";	
    }
}