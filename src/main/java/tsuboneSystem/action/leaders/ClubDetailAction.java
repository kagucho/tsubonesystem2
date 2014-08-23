package tsuboneSystem.action.leaders;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.form.ClubForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class ClubDetailAction {
	
	/** アクションネーム */
	public String actionName = "ClubDetail";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected ClubForm clubForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginLeadersDto用のDto */
	@Resource
	public LoginLeadersDto loginLeadersDto;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
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
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	//部の詳細を取得する
    	TClub club = tClubService.findById(clubForm.id);
    	Beans.copy(club, clubForm).execute();
    	clubForm.tMember = club.tLeaders.tMember;
    	
    	//部に所属している人一覧用のリストの初期化
    	clubForm.tMemberList = new ArrayList<TMember>();
    	
    	//選択されている部のIDでtMemberClubを検索し、取得したリストからメンバーの情報をリストにaddしていく
    	clubForm.tMemberClubList = tMemberClubService.findByIdFortMember(clubForm.id);
    	for (TMemberClub memberClubOne : clubForm.tMemberClubList) {
    		clubForm.tMemberList.add(memberClubOne.tMember);
    	}
    	
    return "clubDetail.jsp";
	}
    
    /** メール配信　*/
    //入力画面
    @Execute(validator = false)
    public String contentRegist(){	
    	// 2重送信防止のためのTokenの生成
        TokenProcessor.getInstance().saveToken(request);
    return viewinput();	
    }
    
  //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "clubMailRegist.jsp";
    }
 
    //確認画面
    @Execute(validator = false)
    public String confirmMail(){
    	//部の所属する人にメールを送る
    	clubForm.tMemberSendList = clubForm.tMemberList;
    return "clubMailConfirm.jsp";	
    }
    
    //完了画面
    @Execute(validator = false)
    public String complete(){
	
    	// 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	clubForm.registMemberId = loginMemberDto.memberId;
        	
        	//TMailにメールの内容を追加する
        	TMail tMail = new TMail();
        	Beans.copy(clubForm, tMail).execute();
        	
        	//メールを送信する
        	MailManager manager = new MailManager();
        	manager.setTitle(clubForm.title);
        	manager.setContent(clubForm.content);
        	manager.setToAddress(clubForm.tMemberSendList.toArray(new TMember[0]));
        	manager.setLogFlg(true, loginMemberDto.memberId, tMailSendMemberService, tMailService);
        	if (manager.sendMail()) {
        		mailMsg = "メールを正常に送信しました。";
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        	}	
        }  
    return "clubMailComplete.jsp";	
    }
}