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
import tsuboneSystem.service.TMailService;

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
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 入力画面(送信先選択) */
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
        TMail mail  = tMailService.findById(mailForm.id);
        
        //チェックが通らなければ共通エラーページ
        if (!check(mail)) {
        	return "/common/error.jsp";
        }
        Beans.copy(mail, mailForm).execute();
        return "mailDetail.jsp";
	}

    /**
     * 各種チェックを行う
     * @param mail
     * @return 正しいならTRUE
     */
	protected boolean check(TMail mail) {
		return mail != null;
	}

}
