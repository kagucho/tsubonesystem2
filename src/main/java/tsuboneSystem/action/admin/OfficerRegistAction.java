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

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.OfficerForm;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class OfficerRegistAction {
	
	
	public String actionName = "ChiefUpdate";
	
	/** OfficerFormのフォーム */
	@ActionForm
	@Resource
	protected OfficerForm officerForm;
	
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
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 2重登録時のエラーメッセージ */
	public String rePageError = null;
	
	//選択画面(副局長)
	@Execute(validator = false)
	public String subChiefIndex() {
		
		//更新対象
		officerForm.officerKind = LeadersKindCode.SUB_CHIEF.getCode();
		officerForm.officerKindName = LeadersKindCode.SUB_CHIEF.getName();
		return view();
	}
	//選択画面(会計)
		@Execute(validator = false)
		public String accountIndex() {
			
			//更新対象
			officerForm.officerKind = LeadersKindCode.ACCOUNT.getCode();
			officerForm.officerKindName = LeadersKindCode.ACCOUNT.getName();
			return view();
		}
	
	//選択画面(理大祭実行委員会)
	@Execute(validator = false)
	public String ridaisaiIndex() {
		
		//更新対象
		officerForm.officerKind = LeadersKindCode.RIDAISAI.getCode();
		officerForm.officerKindName = LeadersKindCode.RIDAISAI.getName();
		
		return view();
	}
	//選択画面(合宿委員)
	@Execute(validator = false)
	public String gassyukuIndex() {
		
		//更新対象
		officerForm.officerKind = LeadersKindCode.GASSYUKU.getCode();
		officerForm.officerKindName = LeadersKindCode.GASSYUKU.getName();
		
		return view();
	}
	//選択画面(WEB管理)
	@Execute(validator = false)
	public String webAdminIndex() {
		
		//更新対象
		officerForm.officerKind = LeadersKindCode.WEBADMIN.getCode();
		officerForm.officerKindName = LeadersKindCode.WEBADMIN.getName();
		
		return view();
	}
	//選択画面(その他)
	@Execute(validator = false)
	public String etcIndex() {
		
		//更新対象
		officerForm.officerKind = LeadersKindCode.ETC.getCode();
		officerForm.officerKindName = LeadersKindCode.ETC.getName();
		
		return view();
	}
	
	//表示
	@Execute(validator = false)
	public String view() {
		//Memberの選択肢一覧取得
		officerForm.memberItems = tMemberService.findAllOrderById(false);
		return "officerRegistInput.jsp";
	}
	
	//権限選択画面
    @Execute(validator = true, urlPattern = "confirm/{id}", validate="validateBase", input="view", stopOnValidationError = false)
	public String confirm() {
    	//選択されたメンバー情報
    	officerForm.tMemberNew = tMemberService.findById(officerForm.id); 
    	
    	//登録対象が副局長かweb管理であったときは権限選択画面を飛ばす
    	if (officerForm.officerKind.equals(LeadersKindCode.SUB_CHIEF.getCode()) || officerForm.officerKind.equals(LeadersKindCode.WEBADMIN.getCode())) {
    		return confirmSec();
    	}
    	
        return "officerRegistConfirm.jsp";
	}
    
    //確認画面(第2)
    @Execute(validator = false, reset = "resetInput")
	public String confirmSec() {
    	/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        return "officerRegistConfirmSec.jsp";
	}
    
    //登録画面
    @Execute(validator = false)
	public String complete() {
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	if (officerForm.officerKind.equals(LeadersKindCode.SUB_CHIEF.getCode()) || officerForm.officerKind.equals(LeadersKindCode.WEBADMIN.getCode())) {
        		//管理者の登録
        		TAdmin tAdmin = new TAdmin();
        		tAdmin.MemberId = officerForm.tMemberNew.id;
        		tAdmin.OfficerKind = Integer.valueOf(officerForm.officerKind);
        		tAdminService.insert(tAdmin);
        	} else {
        		//管理者以外の登録
        		if(officerForm.memberUpdate){
            		//メンバーの更新権限があれば、秘匿情報は公開される。
            		officerForm.secretInformation = true;
            	}
            	TLeaders tLeaders = new TLeaders();
            	tLeaders.MemberId = officerForm.tMemberNew.id;
            	Beans.copy(officerForm, tLeaders).excludes("memberId","id").execute();
            	tLeadersService.insert(tLeaders);
        	}
            return viewComp();
        } else {
        	rePageError = "2重登録を感知しました。最初から登録しなおしてください。";
        	return viewComp();
        }
	}
    
    @Execute(validator = false)
    public String viewComp(){
    	return "officerRegistComplete.jsp";
    }
	
    
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
		//選択されたid(memberId)でTLeaders(memberId)を検索し該当がなく、局長にも副局長にもなっていないことを確認する。
    	List<TLeaders> leadersOld = tLeadersService.findByMemberIdList(officerForm.id);
    	if (leadersOld.size() > 0) {
    		for (TLeaders tLeaderOne : leadersOld){
    			if (tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.CHIEF.getCode())) || tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.SUB_CHIEF.getCode())) || tLeaderOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))){
    				errors.add("leadersOld",new ActionMessage("このメンバーはすでに局長、もしくは副局長のになっているメンバーです。",false));
    			}
    		}
    	}
    	
    	//選択されたMemberが連絡先をすべて登録しているかを確認する。
    	TMember tMember = tMemberService.findById(officerForm.id);
    	if (StringUtils.isEmpty(tMember.mail) || StringUtils.isEmpty(tMember.tel1) || StringUtils.isEmpty(tMember.tel2) || StringUtils.isEmpty(tMember.tel3)) {
    		errors.add("leadersConnection",new ActionMessage("このメンバーには連絡先のどれかが登録されていません。メール、電話番号をすべて登録するか他のメンバーを選択してください。",false));
    	}
    	
        return errors;
    }
}
