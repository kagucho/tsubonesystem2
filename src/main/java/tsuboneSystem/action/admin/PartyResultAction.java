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


import javax.annotation.Resource;





import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.action.abstracts.PartyOperateAbstractAction;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyResultForm;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyService;

public class PartyResultAction extends PartyOperateAbstractAction{
	
	public String actionName = "PartyResult";
	
	/** PartyResultFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyResultForm partyResultForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDtoのDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** TMemberのサービス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	@Execute(validator = false, urlPattern = "{id}")
	public String input() {
		
		//内容を取得する
		TParty tParty = tPartyService.findById(partyResultForm.id);
		Beans.copy(tParty, partyResultForm).execute();
		
		//keyをclubId, valueをclubNameとしてマップを作成する
		partyResultForm.clubMapSS = tClubService.getClubMap();
		
        return viewinput();
	}
	
    @Execute(validator = false)
	public String viewinput() {
    	return "partyInput.jsp";
    }
    
    @Execute(validator = true, input = "partyInput.jsp", reset = "resetInput")
	public String confirm() {
    	
    	// メールを送る場合は送信対象者をリストに格納する
    	if (partyResultForm.mailSendFlag) {
			//会議の題名をメールのタイトルとする
			StringBuffer bf = new StringBuffer();
			bf.append("「");
			bf.append(partyResultForm.meetingName);
			bf.append("」");
			bf.append("の結果が登録されました");
			partyResultForm.title = new String(bf);
			
			//会議結果をメールの内容とする
			partyResultForm.content = partyResultForm.meetingResult;
			
			//全員に送る場合
			if (StringUtil.isNotEmpty(partyResultForm.activeOrOb)) {
				if ("1".equals(partyResultForm.activeOrOb)) {
					// 現役生のみ
					if (StringUtil.isNotEmpty(partyResultForm.allOrClub)) {
						if ("1".equals(partyResultForm.allOrClub)) {
							// 全員
							partyResultForm.tMemberSendList = tMemberService.findAllOrderById_ForMail(false);
						} else if ("2".equals(partyResultForm.allOrClub)) {
							// 部ごと
							partyResultForm.tMemberSendList = tMemberService.findByClubIds(false, partyResultForm.clubListCheck);
						}
					}
					
				} else if ("2".equals(partyResultForm.activeOrOb)) {
					partyResultForm.tMemberSendList = tMemberService.findOB_ForMail();
				}
			}
    	}
    	
        return "partyConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	//更新
    	TParty tParty = tPartyService.findById(partyResultForm.id);
    	tParty.meetingResult = partyResultForm.meetingResult;
    	tParty.resultEditEndFlag = partyResultForm.resultEditEndFlag;
    	tParty.resultEditMemberId = loginMemberDto.memberId;	
    	tPartyService.update(tParty);
    	
    	//メール送信の必要があれば送信する
    	Beans.copy(partyResultForm, partyForm).execute();
    	if (partyResultForm.mailSendFlag) {
    		sendMail(partyForm, loginMemberDto.memberId);
    	}
    	
        return "partyComplete.jsp";
	}
   
    protected Integer getLoginMemberId() {
    	return loginAdminDto.memberId;
    }
}
