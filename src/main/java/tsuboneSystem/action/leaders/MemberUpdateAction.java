package tsuboneSystem.action.leaders;

import java.util.ArrayList;
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
import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.original.util.DigestUtil;
import tsuboneSystem.service.TAdminService;
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
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** loginLeadersDtoのDTO */
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
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
	
	/** Memberのリスト */
	public List<TMember> memberItems;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String input() {
		
    	/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        if(!loginLeadersDto.memberUpdate){
        	//メンバーの編集権限がない場合は、強制的に自分の情報が更新対象となる
        	memberForm.id = loginLeadersDto.tMemberLogin.id;
        }
        
        //部を表示するマップ
        memberForm.clubMap = tClubService.getClubMapIS();
        
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

        return viewinput();
	}
	
	 //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "memberInput.jsp";
    }
    
    @Execute(validator = true, validate="validateBase", input="memberInput.jsp", stopOnValidationError = false, reset = "resetInput")
	public String confirmUp() {
    	
    	//選択した部を表示する
    	memberForm.tMemberClubList = new ArrayList<TMemberClub>();
        for(String one : memberForm.clubListChecked){
        	TMemberClub tMemberClub = new TMemberClub();
        	tMemberClub.tClub = tClubService.findById(Integer.valueOf(one));
        	memberForm.tMemberClubList.add(tMemberClub);
        }
        
        return "memberConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)){  	
        	TMember memberUp = new TMember();
        	Beans.copy(memberForm, memberUp).execute();
        	
        	//パスワードの更新
        	if (!memberForm.password.isEmpty()){
        		//パスワードのハッシュ化
            	memberUp.password = DigestUtil.md5(memberForm.password);
        	}else{
        		TMember tMember = tMemberService.findById(memberForm.id);
        		memberUp.password = tMember.password;
        	}
        	
        	//不達フラグはfalse
        	memberUp.sendErrorFlag = false;
        	
        	//DB更新
        	tMemberService.update(memberUp);
        	memberForm.id = memberUp.id;

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
        	
        	//編集対象が自分だった場合にはログイン情報を書き換える
        	if(loginMemberDto.memberId.equals(memberForm.id)){
        		loginMemberDto.tMemberLogin = memberUp;
        		loginLeadersDto.tMemberLogin = memberUp;
        	}
        	
        }
    return "memberComplete.jsp";
    }
    
    //オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
       // userNameの重複チェック
        TMember tMember = tMemberService.findByUserName(memberForm.userName);	
		if (tMember != null && !tMember.id.equals(memberForm.id)) {
			errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
		}
		
		//所属部の必須チェック
		if(memberForm.clubListChecked.size() == 0){
			errors.add("department",new ActionMessage("部の選択は必須です。",false));
		}
		
		//役職に就いている人はOB宣言できない
		if("true".equals(memberForm.obFlag)){
			TAdmin tAdmin = tAdminService.findById(memberForm.id);
			List<TLeaders> tLeadersList = tLeadersService.findByMemberIdList(memberForm.id);
			if(tAdmin != null || tLeadersList.size() > 0){
				memberForm.obFlag = "false";
				errors.add("obFlag",new ActionMessage("役職についている人はOB宣言出来ません。",false));
			}
		}
		
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
    			}else if (tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.GASSYUKU.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.RIDAISAI.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ETC.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))) {
    				//部長以外の場合
    				if (memberForm.mail.isEmpty() || memberForm.tel1.isEmpty() || memberForm.tel2.isEmpty() || memberForm.tel3.isEmpty()) {
                		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため、連絡先を空白にすることはできません",false));
                	}
    			}
    		}
    	}
    	
    	//管理者の情報は編集できない
    	List<TAdmin> tAdminList = tAdminService.findByMemberIdList(memberForm.id);
    	if(tAdminList.size() > 0){
    		errors.add("OfficerCheck",new ActionMessage("このメンバーは管理者であり、編集には権限が必要です。",false));
    	}
		
        return errors;
    }
  
}
    
