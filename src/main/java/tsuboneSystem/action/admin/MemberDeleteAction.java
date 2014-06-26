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

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberDeleteAction {
	
	public String actionName = "MemberDelete";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String index() {
		
		TMember member = tMemberService.findById(memberForm.id);
		Beans.copy(member, memberForm).execute();
        
        /** 詳細画面にて部の表示のためにmapを作成する　**/
        memberForm.clubList = tClubService.findAllOrderById();
        memberForm.clubMap = new HashMap<Integer,String>();
        for ( TClub club : memberForm.clubList) {
        	memberForm.clubMap.put(club.id, club.ClubName);
        }
        
        //パスワードは表示しない
        memberForm.password = "(パスワードは初期化のみ可能です)";
       
		
        return "memberConfirm.jsp";
	}
    
    @Execute(validator = false, validate="validateBase", input="memberConfirm.jsp", stopOnValidationError = false)
	public String complete() {
    	
    	TMember member = tMemberService.findById(memberForm.id);
    	member.deleteFlag = true;
    	tMemberService.update(member);
    	
    return "memberComplete.jsp";
    }
    
  //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
    	
    	//選択されたMemberが現役の部長以上の役職に付いている場合、連絡先をすべて登録しているかを確認する。
    	List<TLeaders> tLeadersList = tLeadersService.findByMemberIdList(memberForm.id);
    	if (tLeadersList.size() > 0) {
    		for (TLeaders tLeadersOne : tLeadersList) {
    			TClub tClub = tClubService.findByLeadersId(tLeadersOne.id);
    			if (tClub != null) {
    				//各部の現役の部長の場合
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため削除できません",false));
    			}else if (tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.CHIEF.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.SUB_CHIEF.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))) {
    				//局長もしくは副局長の場合
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため削除できません",false));
    			}
    		}
    	}	
        return errors;
    }
}
    
