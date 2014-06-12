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
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SexCode;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberUpdateAction {
	
	public String actionName = "MemberUpdate";
	
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
	
	/** ログインDTO */
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	
	@SuppressWarnings("boxing")
	@Execute(validator = false)
	public String input() {
		
    	/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        memberForm.id = loginIndividualsDto.tMemberLogin.id;
        
        /** 詳細画面にて部の表示のためにmapを作成する　**/
        memberForm.clubList = tClubService.findAllOrderById();
        memberForm.clubMap = new HashMap<Integer,String>();
        for ( TClub club : memberForm.clubList) {
        	memberForm.clubMap.put(club.id, club.ClubName);
        }
        
      //すでに所属している部のチェックボックスはonにする
        memberForm.tMemberClubUpOldId = tMemberClubService.findByMemberId(memberForm.id.toString());
        for (TMemberClub tMemberClubUpOldOne : memberForm.tMemberClubUpOldId){
        	memberForm.clubListChecked.add(tMemberClubUpOldOne.ClubId.toString());
        };
        
        memberForm.sexMap = new HashMap<String, String>();
        for (Integer i=1; i<=3; i++) {
        	memberForm.sexMap.put(i.toString(), SexCode.getnameByCode(i.toString()));
        }
		
		TMember member = tMemberService.findById(memberForm.id);
		Beans.copy(member, memberForm).excludes("password").execute();
		memberForm.password = null;

        return "memberInput.jsp";
	}
    
    @Execute(validator = true, input="memberInput.jsp")
	public String confirmUp() {
    	
    	/** 詳細画面にて部の表示のためにmapを作成する　**/
        //登録されている部をすべてリストの形で呼び出す
        memberForm.clubList = tClubService.findAllOrderById();
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        memberForm.clubMapSS = new HashMap<String,String>(); 
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : memberForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	memberForm.clubMapSS.put(club.id.toString(), club.ClubName);
        }
        
        return "memberConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)){  	
        	TMember memberUp = new TMember();
        	Beans.copy(memberForm, memberUp).execute();
        	memberUp.obFlag = false;
        	//ログインDTOを入れなおす
        	loginIndividualsDto.tMemberLogin = memberUp;
        	
        	if (!memberForm.password.isEmpty()){
        		//パスワードのハッシュ化
            	memberUp.password = DigestUtil.md5(memberForm.password);
        	}else{
        		TMember tMember = tMemberService.findById(memberForm.id);
        		memberUp.password = tMember.password;
        	}
        	
        	//DB更新
        	tMemberService.update(memberUp);

        	/** メンバーが所属していた情報を一回削除した上で、選択された部とメンバーのIDをtMemberClubに登録していく。複数なので選択した回数だけレコードを登録する。*/
        	//メンバーが所属していた情報を削除する
        	memberForm.tMemberClubUpOldId = tMemberClubService.findByMemberId(memberForm.id.toString());
        	for (TMemberClub tMemberClubUpOldOne : memberForm.tMemberClubUpOldId) {
        		//DB削除
        		tMemberClubService.delete(tMemberClubUpOldOne);
        	}
        	
        	//新しく選択された情報で新規追加する。
        	for (String check : memberForm.clubListChecked){
        		TMemberClub memberClub = new TMemberClub();
        		memberClub.MemberId = memberUp.id;
        		memberClub.ClubId = Integer.valueOf(check);
        		//DB登録
        		tMemberClubService.insert(memberClub);
        	}
        }
    return "memberComplete.jsp";
    }
  
}
    
