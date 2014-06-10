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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.ClubForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class ClubDetailAction {
	
	public String actionName = "ClubDetail";
	
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
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
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
}
