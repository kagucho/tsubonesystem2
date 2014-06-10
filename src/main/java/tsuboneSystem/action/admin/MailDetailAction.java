package tsuboneSystem.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.form.MailForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class MailDetailAction {
	
	/** アクションネーム */
	public String actionName = "MailDetail";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** Memberのアクションフォーム */
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
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
        
        /* メンバーの一覧を取得する　*/
        TMail mail  = tMailService.findById(mailForm.id);
        Beans.copy(mail, mailForm).execute();
 
        return "mailDetail.jsp";
	}

}
