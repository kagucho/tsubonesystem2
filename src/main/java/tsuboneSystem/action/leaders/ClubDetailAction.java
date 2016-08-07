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

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.ClubForm;
import tsuboneSystem.original.util.MailManagerUtil;
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
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginLeadersDto用のDto */
	@Resource
	public LoginLeadersDto loginLeadersDto;
	
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
	
	/** 部長であったらtrue */
	public boolean updateFlag = false;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
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
    	
    	//部長であったら編集ボタンが出現する
    	if (club.tLeaders.tMember.id.equals(loginLeadersDto.memberId)) {
    		updateFlag = true;
    	}
    	
    return "clubDetail.jsp";
	}
    
    /** メール配信　*/
    //入力画面
    @Execute(validator = false)
    public String contentRegist(){	
    	// 2重送信防止のためのTokenの生成
        TokenProcessor.getInstance().saveToken(request);
    return viewinput();	
    }
    
  //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "clubMailRegist.jsp";
    }
 
    //確認画面
    @Execute(validator = false)
    public String confirmMail(){
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
        	clubForm.registMemberId = loginMemberDto.memberId;
        	
        	//TMailにメールの内容を追加する
        	TMail tMail = new TMail();
        	Beans.copy(clubForm, tMail).execute();
        	
        	//メールを送信する
        	MailManagerUtil mailUtil = new MailManagerUtil();
        	mailUtil.setRegistId(loginMemberDto.memberId);
        	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
        	mailUtil.setTitle(clubForm.title);
        	mailUtil.setContent(clubForm.content);	
        	mailUtil.setLinkUrlFlag(false);
        	mailUtil.setToAddressActorSplit(clubForm.tMemberSendList);
        	mailUtil.sendMail();
        	if (!mailUtil.getSendMailResult()) {
        		mailMsg = "メールを正常に送信しました。";
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        	}	
        }  
    return "clubMailComplete.jsp";	
    }
}
