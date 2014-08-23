package tsuboneSystem.action.tempRegist;

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

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.code.SexCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TTempLogin;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
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
	
	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** TTempLoginServiceのサービスクラス */
	@Resource
	protected TTempLoginService tTempLoginService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
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
        	
        	//仮登録フラグをtrueにする
        	member.tempMemberFlag = true;
        	
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
        	
        	//仮登録情報を管理者にメールで通知する
        	String title;
        	String content;
        	
        	//タイトル
        	title = "メンバーの仮登録がされました";
        	
        	//内容
        	StringBuffer buff = new StringBuffer();
        	buff.append("メンバーの新規仮登録がされましたのでお知らせします。");
        	buff.append("\n");
        	buff.append("登録者：");
        	buff.append(memberForm.hname);
        	buff.append("(");
        	buff.append(memberForm.name);
        	buff.append(")");
        	buff.append("\n");
        	buff.append("OB宣言：");
        	if(memberForm.obFlag != null){
        		buff.append("OB");
        	}else{
        		buff.append("(現役部員)");
        	}
        	buff.append("(");
        	buff.append(memberForm.entrance);
        	buff.append("年入学");
        	buff.append(")");
        	buff.append("\n");
        	buff.append("連絡先：");
        	buff.append(memberForm.mail);
        	buff.append("\n");
        	buff.append("\n");
        	buff.append("仮登録メンバーは、管理者が承認して有効になります。今しばらくお待ちください。承認時にメールでお知らせします。");
        	content = new String(buff);
        	
        	//送信相手(部長・web管理・本人)
        	List<TMember> tMemberSendList = new ArrayList<TMember>();
        	List<TAdmin> admin = new ArrayList<TAdmin>();
        	admin.addAll(tAdminService.findByKind(LeadersKindCode.CHIEF.getCode()));//部長
        	admin.addAll(tAdminService.findByKind(LeadersKindCode.WEBADMIN.getCode()));//web管理
        	for(TAdmin one : admin){
        		tMemberSendList.add(one.tMember);
        	}
        	tMemberSendList.add(member);//本人
        	
        	//送信
        	MailManager manager = new MailManager();
        	manager.setTitle(title);
        	manager.setContent(content);
        	manager.setToAddress(tMemberSendList.toArray(new TMember[0]));
        	manager.setLogFlg(true, null, tMailSendMemberService, tMailService);
        	if (!manager.sendMail()) {
        		//TODO エラー時の処理
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
        TTempLogin tTempLogin = tTempLoginService.findByUserName(memberForm.userName);
		if (tMember != null || tTempLogin != null) {
			errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
		}
		
		//所属部の必須チェック
		if(ArrayUtil.isEmpty(memberForm.clubListCheck)){
			errors.add("department",new ActionMessage("部の選択は必須です。",false));
		}
        return errors;
    }
}
