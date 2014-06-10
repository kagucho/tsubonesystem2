package tsuboneSystem.action.admin;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MailForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
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
    @Execute(validator = false)
	public String index() {
    	
    	/** 2重送信防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        /* メンバーの一覧を取得する　*/
        mailForm.tMemberItem = tMemberService.findAllOrderById();
 
        return "index.jsp";
	}
    
    /** 入力画面(メール内容) */
    @Execute(validator = false)
	public String contentRegist() {
        return "contentRegist.jsp";
	}
    
    /** 確認画面 */
    @Execute(validator = false)
	public String confirm() {
    	
    	mailForm.tMemberSendList = new ArrayList<TMember>();
    	for (String one : mailForm.memberSelect) {
    		mailForm.tMemberSendList.add(tMemberService.findById(Integer.valueOf(one)));
    	}
    	
        return "mailConfirm.jsp";
	}
    
    /** 完了画面 */
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	mailForm.registMemberId = loginAdminDto.memberId;
        	
        	//TMailにメールの内容を追加する
        	TMail tMail = new TMail();
        	Beans.copy(mailForm, tMail).execute();
        	tMailService.insert(tMail);
        	
        	//TMailSendAttendにメールの送信相手を追加する
        	for (TMember tMemberOne : mailForm.tMemberSendList) {
        		TMailSendMember tMailSendMember = new TMailSendMember();
        		tMailSendMember.mailId = tMail.id;
        		tMailSendMember.memberId = tMemberOne.id;
        		tMailSendMemberService.insert(tMailSendMember);
        	}
        	
        	//メールを送信する
        	MailManager manager = new MailManager(loginAdminDto.memberId);
        	manager.setTitle(mailForm.title);
        	manager.setContent(mailForm.content);
        	manager.setToAddress(mailForm.tMemberSendList.toArray(new TMember[0]));
        	if (manager.sendMail()) {
        		mailMsg = "メールを正常に送信しました。";
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        	}
        	
        	
        }
        return "mailComplete.jsp";
	}
}
