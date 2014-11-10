package tsuboneSystem.action.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.EnqueteReplyForm;
import tsuboneSystem.service.TEnqueteSelectService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class EnqueteReplyAction {

	/** アクションネーム */
	public String actionName = "EnqueteReply";

	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected EnqueteReplyForm enqueteReplyForm;

	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** TEnqueteSelectのサービスクラス */
	@Resource
	protected TEnqueteSelectService tEnqueteSelectService;

	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;

	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;

	/** ログインユーザ*/
	@Resource
	protected LoginAdminDto loginAdminDto;

	public final int num = 1;




    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	// 選択肢を取得する
    	Map<String, String> enqueteSelectMap = tEnqueteSelectService.enqueteSelectMap(enqueteReplyForm.id);

    	enqueteReplyForm.enqueteSelectMap = enqueteSelectMap;

    	enqueteReplyForm.memberId = loginAdminDto.memberId;

    	return "enqueteReply.jsp";
	}

    @Execute(validator = false)
    public String confirm() {
    	return "enqueteReplyConfirm.jsp";
    }
}
