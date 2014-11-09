package tsuboneSystem.action.individuals;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MailListForm;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;


public class MailListAction {

	/** actionの名前　*/
	public String actionName = "MailList";

	/** MailListのアクションフォーム */
	@ActionForm
	@Resource
	protected MailListForm mailListForm;

	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;

	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

    @Execute(validator = false)
	public String index() {

    	//名前を表示するためのマップ(OBを含む)
    	mailListForm.memberMapIS = new HashMap<Integer,String>();
    	mailListForm.tMemberAllList = tMemberService.findAllOrderById(true);
    	for (TMember memberOne : mailListForm.tMemberAllList) {
    		mailListForm.memberMapIS.put(memberOne.id, memberOne.hname);
    	}

    	//メールの一覧(とりあえず最初の２０件だけ取得する)
    	mailListForm.tMailItem = getMailRecord();

        return "index.jsp";
	}


    @Execute(validator = false)
    public String onSearch() {

    	//名前を表示するためのマップ(OBを含む)
    	mailListForm.memberMapIS = new HashMap<Integer,String>();
    	mailListForm.tMemberAllList = tMemberService.findAllOrderById(true);
    	for (TMember memberOne : mailListForm.tMemberAllList) {
    		mailListForm.memberMapIS.put(memberOne.id, memberOne.hname);
    	}

    	//メールの一覧(自分に届いたメールのみ)
    	mailListForm.tMailItem = getTMailSendMemberlRecord();


    	return "index.jsp";
    }

	protected List<TMail> getMailRecord() {
		return tMailService.findAllOrderByIdLimitOffset(MailBrowsingRightsCode.MEMBER.getCodeNumber(), 20, 0);
	}

	protected List<TMail> getTMailSendMemberlRecord() {
		return tMailSendMemberService.findAllOrderByIdLimitOffset(mailListForm, loginMemberDto.memberId, MailBrowsingRightsCode.MEMBER.getCodeNumber(), 20, 0);
	}
}
