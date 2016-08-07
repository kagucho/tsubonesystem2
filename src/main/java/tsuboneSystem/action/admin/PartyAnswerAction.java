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

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.code.PartyAnswerCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TPartyAnswer;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.PartyQuestionAnswerForm;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TPartyAnswerService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyQuestionService;
import tsuboneSystem.service.TPartyService;

public class PartyAnswerAction {
	
	public String actionName = "PartyDetail";
	
	/** PartyQuestionAnswerFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyQuestionAnswerForm partyQuestionAnswerForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService  tPartyClubService;
	
	@Resource
	TMailService tMailService;
	
	@Resource
	TMailSendMemberService tMailSendMemberService;
	
	/** TPartyAnswerServiceのサービスクラス */
	@Resource
	protected TPartyAnswerService  tPartyAnswerService;
	
	/** TPartyQuestionServiceのサービスクラス */
	@Resource
	protected TPartyQuestionService  tPartyQuestionService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{partyId}/{questionId}", reset = "resetInput")
	public String index() {
    	
    	partyQuestionAnswerForm.tPartyQuestion = tPartyQuestionService.findById(partyQuestionAnswerForm.questionId);
    	
    	//メール送信対象のマップ
    	partyQuestionAnswerForm.answerSendKindMap = new HashMap<String, String>();
        for (Integer i=0; i<=2; i++) {
        	partyQuestionAnswerForm.answerSendKindMap.put(i.toString(), PartyAnswerCode.getnameByCode(i.toString()));
        }
    	
        return viewinput();
	}
    
  //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyAnswerInput.jsp";
    }
    
    //質問確認画面
    @Execute(validator = true, input = "partyAnswerInput.jsp")
    public String answerConfirm(){
    	
    	return "partyAnswerConfirm.jsp";
    }
    
    //質問完了画面
    @SuppressWarnings("boxing")
	@Execute(validator = false)
    public String answerComplete(){
    	
    	/** 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//質問内容を登録する
        	TPartyAnswer tPartyAnswer = new TPartyAnswer();
        	tPartyAnswer.questionId = partyQuestionAnswerForm.questionId;
        	tPartyAnswer.memberId = getLoginMemberId();
        	tPartyAnswer.answer = partyQuestionAnswerForm.answer;
        	tPartyAnswer.answerSendKind = partyQuestionAnswerForm.answerSendKind;
        	tPartyAnswerService.insert(tPartyAnswer);
        	
        	//条件にあった会員にメールを送る
        	if(partyQuestionAnswerForm.answerSendKind != Integer.parseInt(PartyAnswerCode.NO_SEND.getCode())){
        		//送信先を取得
        		ArrayList<TMember> tSendMember = getMailSendMember();
        		//メールを送る
        		if (!sendMail(tSendMember)) {
        			//TODO 送信に失敗した場合どうするか
        		};
        	}
        }
    	return "partyAnswerComplete.jsp";
    }

    /**
     * メールを送信する
     * @param tSendMember
     */
	protected boolean sendMail(ArrayList<TMember> tSendMember) {
		//タイトルを取得
		String title = partyQuestionAnswerForm.tPartyQuestion.getQuesutionTitle();
		
		//内容を取得
		String content = partyQuestionAnswerForm.tPartyQuestion.getQuestionContent(getLoginTMember().hname, partyQuestionAnswerForm.answer);
		
		//メールを送信する
    	MailManagerUtil mailUtil = new MailManagerUtil();
    	mailUtil.setRegistId(loginMemberDto.memberId);
    	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
    	mailUtil.setTitle(title);
    	mailUtil.setContent(content);	
    	mailUtil.setLinkUrlFlag(false);
    	mailUtil.setToAddressActorSplit(tSendMember);
    	mailUtil.sendMail();

		return mailUtil.getSendMailResult();
	}

	/**
	 * メール送信先を取得する
	 * @return
	 */
	protected ArrayList<TMember> getMailSendMember() {
		ArrayList<TMember> tSendMember = new ArrayList<TMember>();
		
		//送信対象
		if (partyQuestionAnswerForm.answerSendKind == Integer.parseInt(PartyAnswerCode.QUESTIONER.getCode())) {
			//質問者のみ
			tSendMember.add(partyQuestionAnswerForm.tPartyQuestion.tMember);
		}else if (partyQuestionAnswerForm.answerSendKind == Integer.parseInt(PartyAnswerCode.ALL.getCode())) {
			//質問者と会議の出席者全員
			for(TPartyAttend one : partyQuestionAnswerForm.tPartyQuestion.tParty.tPartyAttendList){
				tSendMember.add(one.tMember);
			}
			if(tPartyAttendService.findByMemberIdWithPartyId(partyQuestionAnswerForm.tPartyQuestion.memberId.toString(), partyQuestionAnswerForm.partyId) == null){
				//質問者が出席対象者以外だった場合
				tSendMember.add(partyQuestionAnswerForm.tPartyQuestion.tMember);
			}
		}
		return tSendMember;
	}
    
    /**
     * ログイン中の会員のIDを返す
     * @return
     */
    protected Integer getLoginMemberId() {
    	return loginAdminDto.memberId;
    }
    
    /**
     * ログイン中の会員のTMemberEntityを返す
     */
    protected TMember getLoginTMember() {
    	return loginAdminDto.tMemberLogin;
    }
}
