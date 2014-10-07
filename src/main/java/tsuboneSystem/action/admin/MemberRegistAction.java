package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SexCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TTempLoginService;


public class MemberRegistAction {
	
	/** actionの名前　*/
	public String actionName = "MemberRegist";
	
	/** 2重登録時のエラーメッセージ */
	public String rePageError = null;
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
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
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** TTempLoginServiceのサービスクラス */
	@Resource
	protected TTempLoginService tTempLoginService;
	
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
        
        memberForm.id = null;
        
        return viewinput();
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "memberInput.jsp";
    }
    
    //確認画面
    @Execute(validator = true, validate="validateBaseAdmin", input="viewinput", stopOnValidationError = false)
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
    
}
