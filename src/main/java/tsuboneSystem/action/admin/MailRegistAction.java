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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MailForm;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MailRegistAction {
	
	/** アクションネーム */
	public String actionName = "MailRegist";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** MailFormのアクションフォーム */
	@ActionForm
	@Resource
	protected MailForm mailForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 入力画面(送信先選択) */
    @Execute(validator = false, reset = "resetInput")
	public String index() {
    	
    	/** 2重送信防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        mailForm.clubMapSS = tClubService.getClubMap();

        return viewinput();
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "index.jsp";
    }
    
    /** 確認画面 */
    @Execute(validator = true, input = "index.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
	public String confirm() {
    	
    	//全員に送る場合
    	if (StringUtil.isNotEmpty(mailForm.activeOrOb)) {
    		if ("1".equals(mailForm.activeOrOb)) {
    			// 現役生のみ
    			if (StringUtil.isNotEmpty(mailForm.allOrClub)) {
    				if ("1".equals(mailForm.allOrClub)) {
    					// 全員
    					mailForm.tMemberSendList = tMemberService.findAllOrderById_ForMail(false);
    				} else if ("2".equals(mailForm.allOrClub)) {
    					// 部ごと
    					mailForm.tMemberSendList = tMemberService.findByClubIds(false, mailForm.clubListCheck);
    				} else if ("3".equals(mailForm.allOrClub)) {
    	    			// 役職に就いているメンバーのみ
    	    			mailForm.tMemberSendList = new ArrayList<TMember>();
    	    			// 重複がないメンバーId
    	    			Set<Integer> memberIdSet = new HashSet<Integer>();
    	    			
    	    			// admin
    	    			List<TAdmin> adminList = tAdminService.findAllOrderById();
    	    			// Leaders
    	    			List<TLeaders> leadersList = tLeadersService.findAllOrderById();
    	    			
    	    			// 重複のない送信一覧
    	    			for (TAdmin tAdmin : adminList) {
    	    				memberIdSet.add(tAdmin.tMember.id);
    	    			}
    	    			for (TLeaders tLeaders : leadersList) {
    	    				memberIdSet.add(tLeaders.tMember.id);
    	    			}
    	    			for (Integer memberId : memberIdSet) {
    	    				mailForm.tMemberSendList.add(tMemberService.findById(memberId));
    	    			}
    				}
    			}
    		} else if ("2".equals(mailForm.activeOrOb)) {
    			// OBのみ
    			mailForm.tMemberSendList = tMemberService.findOB_ForMail();
    		}
    	}
		return "mailConfirm.jsp";
	}
    
    /** 完了画面 */
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	mailForm.registMemberId = getLoginMemberId();
        	
        	//メールを送信する
        	MailManagerUtil mailUtil = new MailManagerUtil();
        	mailUtil.setRegistId(loginMemberDto.memberId);
        	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
        	mailUtil.setTitle(mailForm.title);
        	mailUtil.setContent(mailForm.content);	
        	mailUtil.setLinkUrlFlag(false);
        	mailUtil.setToAddressActorSplit(mailForm.tMemberSendList);
        	mailMsg = mailUtil.sendMail();
        	
        	return "mailComplete.jsp";
        } else {
        	return "/common/error.jsp";
        }
	}

	protected Integer getLoginMemberId() {
		return loginAdminDto.memberId;
	}
	
	protected TMember getLoginTMember() {
		return loginAdminDto.tMemberLogin;
	}
}
