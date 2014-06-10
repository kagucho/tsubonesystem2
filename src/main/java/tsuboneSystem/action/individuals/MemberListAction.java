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

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberListAction {
	
	public String actionName = "MemberList";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** LoginIndividualsDtoのDto */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
    @Execute(validator = false)
	public String index() {
    	
    	//ログインしているメンバーの情報を取得する
    	memberForm.tMember = loginIndividualsDto.tMemberLogin;
    	
    	/** 一覧画面にて部の表示のためにmapを作成する　**/
        //登録されている部をすべてリストの形で呼び出す
        memberForm.clubList = tClubService.findAllOrderById();
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        memberForm.clubMap = new HashMap<Integer,String>();
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : memberForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	memberForm.clubMap.put(club.id, club.ClubName);
        }
    	
        /**　登録されているメンバーの一覧を取得する。　**/
    	memberItems = tMemberService.findByAllOrderEntrance();
    	
        return "memberList.jsp";
	}
}
