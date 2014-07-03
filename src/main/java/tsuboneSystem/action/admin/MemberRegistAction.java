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
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.util.ArrayUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SexCode;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;


public class MemberRegistAction {
	
	/** actionの名前　*/
	public String actionName = "MemberRegist";
	
	/** 2重登録時のエラーメッセージ */
	public String rePageError = null;
	
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
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
    	
    	/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        //部のマップ
        memberForm.clubMapSS = tClubService.getClubMap();

        //性別のマップ
        memberForm.sexMap = SexCode.getSexCodeMap();
        
        return viewinput();
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "memberInput.jsp";
    }
    
    //確認画面
    @Execute(validator = true, validate="validateBase", input="viewinput", stopOnValidationError = false)
	public String confirm() {
    	
    	//選択した部を表示する
    	memberForm.tMemberClubList = new ArrayList<TMemberClub>();
        for(String one : memberForm.clubListCheck){
        	TMemberClub tMemberClub = new TMemberClub();
        	tMemberClub.tClub = tClubService.findById(Integer.valueOf(one));
        	memberForm.tMemberClubList.add(tMemberClub);
        }
        
        return "memberConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	//フォームの内容をエンティティにコピーする
        	TMember member = Beans.createAndCopy(TMember.class, memberForm).execute();
        	
        	//OBフラグは基本false
        	if (memberForm.obFlag == null) {
        		member.obFlag = false;
        	}
        	
        	//削除フラグはfalse
        	member.deleteFlag = false;
        	
        	//エンティティの内容をDBに追加する
        	tMemberService.insert(member);
        	
        	//完了画面から詳細画面に遷移するためにIDを取得する
        	memberForm.id = member.id;
        	      	
        	//選択された部とメンバーのIDをtMemberClubに登録していく。複数なので選択した回数だけレコードを登録する。
        	for (String check : memberForm.clubListCheck){
        		TMemberClub memberClub = new TMemberClub();
        		memberClub.MemberId = member.id;
        		memberClub.ClubId = Integer.valueOf(check);
        		tMemberClubService.insert(memberClub);
        	}	
        }else{
        	rePageError = "2重登録を感知しました。最初から登録しなおしてください。";
        	return viewComp();
        }
        return viewComp();
	}
    
    /** セッションを破棄した後完了画面に遷移(※これをしないと次の新規登録の時に前回の入力情報が残ってしまう)	　**/
    @RemoveSession(name="memberForm")
    public String viewComp(){
    	return "memberComplete.jsp";
    }
    
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
       // userNameの重複チェック
        TMember tMember = tMemberService.findByUserName(memberForm.userName);	
		if (tMember != null) {
			errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
		}
		
		//所属部の必須チェック
		if(ArrayUtil.isEmpty(memberForm.clubListCheck)){
			errors.add("department",new ActionMessage("部の選択は必須です。",false));
		}
        return errors;
    }
}
