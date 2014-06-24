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
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.code.SexCode;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
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
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String input() {
		
		// 2重登録防止のためのTokenの生成
        TokenProcessor.getInstance().saveToken(request);
        
        // 詳細画面にて部の表示のためにmapを作成する
        memberForm.clubList = tClubService.findAllOrderById();
        memberForm.clubMap = new HashMap<Integer,String>();
        for ( TClub club : memberForm.clubList) {
        	memberForm.clubMap.put(club.id, club.ClubName);
        }
        
        // すでに所属している部のチェックボックスはonにする
        memberForm.tMemberClubUpOldId = tMemberClubService.findByMemberId(memberForm.id.toString());
        for (TMemberClub tMemberClubUpOldOne : memberForm.tMemberClubUpOldId){
        	memberForm.clubListChecked.add(tMemberClubUpOldOne.ClubId.toString());
        };
		
        //部長の場合該当の部のチェックボックスはグレーアウト
        List<TLeaders> tLeaders = tLeadersService.findByMemberId_OfficerKindList(memberForm.id.toString(),LeadersKindCode.DIRECTOR.getCode());
        if (tLeaders.size() > 0){
        	//TODO
        }
        
   
        memberForm.sexMap = new HashMap<String, String>();
        for (Integer i=1; i<=3; i++) {
        	memberForm.sexMap.put(i.toString(), SexCode.getnameByCode(i.toString()));
        }
        
		TMember member = tMemberService.findById(memberForm.id);
		Beans.copy(member, memberForm).excludes("password").execute();
		
        return "memberInput.jsp";
	}
    
    @Execute(validator = true, validate="validateBase", input="memberInput.jsp", stopOnValidationError = false)
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
        	
        	if (memberForm.obFlag == null) {
        		memberUp.obFlag = false;
        	}
        	
        	if (!memberForm.password.isEmpty()){
        		//パスワードのハッシュ化
            	memberUp.password = DigestUtil.md5(memberForm.password);
        	}else{
        		TMember tMember = tMemberService.findById(memberForm.id);
        		memberUp.password = tMember.password;
        	}
        	
        	
        	tMemberService.update(memberUp);
        	
        	/** メンバーが所属していた情報を一回削除した上で、選択された部とメンバーのIDをtMemberClubに登録していく。複数なので選択した回数だけレコードを登録する。*/
        	//メンバーが所属していた情報を削除する
        	memberForm.tMemberClubUpOldId = tMemberClubService.findByMemberId(memberForm.id.toString());
        	for (TMemberClub tMemberClubUpOldOne : memberForm.tMemberClubUpOldId) {
        		tMemberClubService.delete(tMemberClubUpOldOne);
        	}
        	
        	//新しく選択された情報で新規追加する。
        	for (String check : memberForm.clubListChecked){
        		TMemberClub memberClub = new TMemberClub();
        		memberClub.MemberId = memberUp.id;
        		memberClub.ClubId = Integer.valueOf(check);
        		tMemberClubService.insert(memberClub);
        	}
        }
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
    				if (memberForm.mail.isEmpty() || memberForm.tel1.isEmpty() || memberForm.tel2.isEmpty() || memberForm.tel3.isEmpty()) {
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため、連絡先を空白にすることはできません",false));
                	}
    			}else if (tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.CHIEF.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.SUB_CHIEF.getCode()))) {
    				//局長もしくは副局長の場合
    				if (memberForm.mail.isEmpty() || memberForm.tel1.isEmpty() || memberForm.tel2.isEmpty() || memberForm.tel3.isEmpty()) {
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため、連絡先を空白にすることはできません",false));
                	}
    			}
    		}
    	}	
        return errors;
    }
    
}
    
