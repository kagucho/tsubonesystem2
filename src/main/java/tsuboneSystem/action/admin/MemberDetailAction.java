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

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SexCode;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberDetailAction {
	
	public String actionName = "MemberDetail";
	
	/** Memberフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;

	
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {
    	
    	/** 詳細画面にて部の表示のためにmapを作成する　**/
        memberForm.clubList = tClubService.findAllOrderById();
        memberForm.clubMap = new HashMap<Integer,String>();
        for ( TClub club : memberForm.clubList) {
        	memberForm.clubMap.put(club.id, club.ClubName);
        }
        
        memberForm.sexMap = new HashMap<String, String>();
        for (Integer i=1; i<=3; i++) {
        	memberForm.sexMap.put(i.toString(), SexCode.getnameByCode(i.toString()));
        }
        
        /** Idから対象のメンバー情報を検索する　**/
        TMember memberDetail = new TMember();
        memberDetail = tMemberService.findById(memberForm.id);
        //検索した結果をformにコピー
        Beans.copy(memberDetail, memberForm).execute();	
        
        /** Idから対象のメンバーが所属している部の一覧を検索する　**/
        memberForm.tMemberClubList = tMemberClubService.findByMemberId(memberForm.id.toString());
        
        //パスワードは表示しない
        memberForm.password = "(パスワードは初期化のみ可能です)";
        	
        return "memberDetail.jsp";
	}
}
