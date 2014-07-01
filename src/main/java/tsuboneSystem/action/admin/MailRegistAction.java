package tsuboneSystem.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MailForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MailRegistAction {
	
	/** アクションネーム */
	public String actionName = "MailRegist";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** MailFormのアクションフォーム */
	@ActionForm
	@Resource
	protected MailForm mailForm;

	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberのサービスクラス */
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
	
	/** 入力画面(送信先選択) */
    @Execute(validator = false, reset = "resetInput")
	public String index() {
    	
    	/** 2重送信防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        mailForm.clubMapSS = tClubService.getClubMapSS();

        return viewinput();
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "index.jsp";
    }
    
    /** 確認画面 */
    @Execute(validator = true, input = "index.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
	public String confirm() {
    	
    	//obを含めるかどうか
    	boolean containsOb = (mailForm.mailSendOBFlag != null);
    	
    	//全員に送る場合
    	if (mailForm.mailSendAllFlag != null) {
    		mailForm.tMemberSendList = tMemberService.findAllOrderById(containsOb);
    	//部ごとに送る場合
    	} else if (mailForm.clubListCheck != null) {
			mailForm.tMemberSendList = tMemberService.findByClubIds(containsOb, mailForm.clubListCheck);
		}	
		return "mailConfirm.jsp";
	}
    
    /** 完了画面 */
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	mailForm.registMemberId = getLoginMemberId();

        	//メールを送信する
        	MailManager manager = new MailManager();
        	manager.setTitle(mailForm.title);
        	manager.setContent(mailForm.content);
        	manager.setToAddress(mailForm.tMemberSendList.toArray(new TMember[0]));
        	manager.setLogFlg(true, getLoginMemberId(), tMailSendMemberService, tMailService);
        	if (!manager.sendMail()) {
        		//TODO エラー時の処理
        	}
        	return "mailComplete.jsp";
        } else {
        	return "/common/error.jsp";
        }
	}

	protected Integer getLoginMemberId() {
		return loginAdminDto.memberId;
	}
	
	protected TMember getLoginTMember() {
		return loginAdminDto.tMemberLogin;
	}
}
