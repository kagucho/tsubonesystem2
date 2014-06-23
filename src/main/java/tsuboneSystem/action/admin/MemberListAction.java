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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberListForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberListAction {
	
	public String actionName = "MemberList";
	
	/** MemberListのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberListForm memberListForm;
	
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
	
	/** 一覧表示件数 */
	final int PAGE_LIMIT = 10;
	
    @Execute(validator = false)
	public String index() {
    	
    	/** 一覧画面にて部の表示のためにmapを作成する　**/
        //登録されている部をすべてリストの形で呼び出す
    	memberListForm.clubList = tClubService.findAllOrderById();
    	
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
    	memberListForm.clubMap = new HashMap<Integer,String>();
    	
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : memberListForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	memberListForm.clubMap.put(club.id, club.ClubName);
        }
	
        /**　登録されているメンバーの一覧を取得する。　**/
    	memberItems = tMemberService.findByAllOrderEntrance();

    	 /**　以下ページャーの作成　**/
    	// 表示するページ数を計算。
    	int pgs = memberItems.size() / PAGE_LIMIT;
    	if (memberItems.size() % PAGE_LIMIT != 0) {
    		pgs = pgs + 1;
    	}
    	
    	// １から最終ページまでの数字を格納するための配列
    	int[] allpages = new int[pgs];
    	for (int i = 0; i < pgs; i++) {
    		allpages[i] = i + 1;
    	}
    	memberListForm.setAllpages(allpages);
    	
    	// 指定ページに該当するデータをリストに入れる
    	ArrayList<TMember> arr = new ArrayList<TMember>();
    	
    	// 指定ページ番号を取得
    	int pages = memberListForm.getPages();
    	
    	// 表示させるデータの最初の番号を計算 ３ページ目ならリストの２０番目
    	int times = PAGE_LIMIT * pages;
    	
    	// リストに入っているbeanの分だけループ
    	for (int l = 0; l < memberItems.size(); l++) {
    		// 指定ページのデータ番号になったらリストに追加
    		if (l >= times) {
    			TMember tMemberOne = memberItems.get(l);
    			arr.add(tMemberOne);
    		}
    		// データ件数が１０件になったら取得終了
    		if (arr.size() == PAGE_LIMIT) {
    		break;
    		}
    	}
    	
        return "memberList.jsp";
	}
    
    //検索
    @Execute(validator = false)
   	public String onSearch() {
       	
       	/** 一覧画面にて部の表示のためにmapを作成する　**/
           //登録されている部をすべてリストの形で呼び出す
       	memberListForm.clubList = tClubService.findAllOrderById();
           //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
       	memberListForm.clubMap = new HashMap<Integer,String>();
           //for文でリストのリストの情報を１つずつマップに入れ込んでいく
           for ( TClub club : memberListForm.clubList) {
           	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
           	memberListForm.clubMap.put(club.id, club.ClubName);
           }

       /**　登録されているメンバーの検索条件に一致するメンバーを一覧表示する。　**/
       if (memberListForm.obFlag == null) {   
    	   memberItems = tMemberService.findBySearch(memberListForm.name,memberListForm.hname,memberListForm.entrance);   
       }else{
    	   memberItems = tMemberService.findBySearchOnOB(memberListForm.name,memberListForm.hname,memberListForm.entrance);
       }
           return "memberList.jsp";
   	}
    
}
