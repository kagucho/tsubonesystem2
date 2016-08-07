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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.form.ClubForm;
import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class ClubRegistAction {
	
	public String actionName = "ClubRegist";
	
	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected ClubForm clubForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
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
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		
		// 2重登録防止のためのTokenの生成
        TokenProcessor.getInstance().saveToken(request);
		
		//部長になり得るメンバーのマップ
		clubForm.memberMap = tMemberService.getMemberMapSS();

		
        return viewinput();
	}
	
	//confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "clubInput.jsp";
    }
    
    @Execute(validator = true, validate="validateBase", input="clubInput.jsp", stopOnValidationError = false)
	public String confirm() {
        return "clubConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)){  	
        	//新しい部長を登録する
        	TLeaders tLeader = new TLeaders();
        	tLeader.OfficerKind = Integer.valueOf(LeadersKindCode.getCodeByName("部長"));
        	tLeader.MemberId = Integer.valueOf(clubForm.OfficerId);
        	tLeadersService.insert(tLeader);
        	
        	//新しいブを登録する
        	TClub tClub = Beans.createAndCopy(TClub.class, clubForm).execute();	
        	tClub.deleteFlag = Boolean.valueOf(false);
        	tClub.LeadersId = tLeader.id;
        	tClubService.insert(tClub);
        	
        	//完了画面から詳細画面のためのID
        	clubForm.id = tClub.id;
        	
        	//新しい部の部員一号(必然的に部長)を登録する
        	TMemberClub memberClub = new TMemberClub();
        	memberClub.MemberId = Integer.valueOf(clubForm.OfficerId);
        	memberClub.ClubId = tClub.id;
        	tMemberClubService.insert(memberClub);
        }
        
        return "clubComplete.jsp";
	}
    
  //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
    	
    	//選択されたMemberが連絡先をすべて登録しているかを確認する。
    	TMember tMember = tMemberService.findById(Integer.valueOf(clubForm.OfficerId));
    	if (StringUtils.isEmpty(tMember.mail) || StringUtils.isEmpty(tMember.tel1) || StringUtils.isEmpty(tMember.tel2) || StringUtils.isEmpty(tMember.tel3)) {
    		errors.add("OfficerId",new ActionMessage("このメンバーには連絡先のどれかが登録されていません。メール、電話番号をすべて登録するか他のメンバーを選択してください。",false));
    	}
    	
        return errors;
    }
}
