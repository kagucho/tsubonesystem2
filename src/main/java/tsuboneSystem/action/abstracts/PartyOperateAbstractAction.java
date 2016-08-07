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

package tsuboneSystem.action.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.entity.TPartySendMail;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartySendMailService;
import tsuboneSystem.service.TPartyService;

public abstract class PartyOperateAbstractAction {
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** tMailServiceのサービスクラス */
	@Resource
	protected TPartySendMailService tPartySendMailService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
    @Execute(validator = true, input = "viewinput", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
	public String confirm() {
    	// メールを送る場合は送信対象者をリストに格納する
    	if (partyForm.mailSendFlag) {
    		
    		// メールのタイトルは会議名とする
    		StringBuffer bf = new StringBuffer();
    		bf.append("【イベント登録】 ");
    		bf.append(partyForm.meetingName);
    		partyForm.title = new String(bf);
    		
    		// メール本文は会議の詳細とする
    		partyForm.content = partyForm.meetingMemo;
    		
			//全員に送る場合
			if (StringUtil.isNotEmpty(partyForm.activeOrOb)) {
				if ("1".equals(partyForm.activeOrOb)) {
					// 現役生のみ
					if (StringUtil.isNotEmpty(partyForm.allOrClub)) {
						if ("1".equals(partyForm.allOrClub)) {
							// 全員
							partyForm.tMemberSendList = tMemberService.findAllOrderById_ForMail(false);
						} else if ("2".equals(partyForm.allOrClub)) {
							// 部ごと
							partyForm.tMemberSendList = tMemberService.findByClubIds(false, partyForm.clubListCheck);
						}
					}
					
				} else if ("2".equals(partyForm.activeOrOb)) {
					partyForm.tMemberSendList = tMemberService.findOB_ForMail();
				}
			}
    	}
    	return "partyConfirm.jsp";
	}
	
	/**
	 * TMemberClubリストからTMemberのリストを取得
	 * @param tMemberClubList
	 * @return
	 */
	protected ArrayList<TMember> getTMemberByTMemberClubList(List<TMemberClub> tMemberClubList) {
		ArrayList<TMember> rtnList = new ArrayList<TMember>();
		for (TMemberClub tMemberClub : tMemberClubList) {
			rtnList.add(tMemberClub.tMember);
		}
		return rtnList;
	}
	
	abstract public String complete();
	/**
	 * メールの配信処理
	 * @param partyForm
	 * @param registMemberId
	 * @return
	 */
	protected boolean sendMail(PartyForm partyForm, int registMemberId) {
    	
    	//メールの送信者のID
    	partyForm.registMemberId = Integer.valueOf(registMemberId);
    	
    	StringBuffer bf = new StringBuffer();
    	bf.append(partyForm.content);
    	bf.append("\n");
    	bf.append("\n");
    	bf.append("このイベントの詳細はこちらから");
    	partyForm.content = new String(bf);
    	
    	//メールを送信する
    	MailManagerUtil mailUtil = new MailManagerUtil();
    	mailUtil.setRegistId(loginMemberDto.memberId);
    	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
    	mailUtil.setTitle(partyForm.title);
    	mailUtil.setContent(partyForm.content);	
    	mailUtil.setContentId(partyForm.id);
    	mailUtil.setContentName("partyDetail/detail");
    	mailUtil.setLinkUrlFlag(true);
    	mailUtil.setToAddressActorSplit(partyForm.tMemberSendList);
    	mailUtil.sendMail();

    	//会議とメールを紐付けるDBに登録する
    	TPartySendMail tPartySendMail = new TPartySendMail();
    	tPartySendMail.PartyId = partyForm.id;
    	tPartySendMail.MailId = mailUtil.getTMail().id;
    	tPartySendMailService.insert(tPartySendMail);
    	
    	return mailUtil.getSendMailResult();	
    }
	
	/**
	 * PartClubテーブルに情報を挿入する
	 * @param partyId
	 */
    protected void insertTPartyClub(Integer partyId) {
    	if (partyForm.attendClub == null) {
    		return;
    	}
    	for (String clubId : partyForm.attendClub) {
    		TPartyClub tPartyClub = new TPartyClub();
			tPartyClub.PartyId = partyId;
			tPartyClub.ClubId = Integer.valueOf(clubId);
			tPartyClubService.insert(tPartyClub);
    	}
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyInput.jsp";
    }
	
	abstract protected Integer getLoginMemberId();
}
