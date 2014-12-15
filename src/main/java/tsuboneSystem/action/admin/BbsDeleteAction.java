package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.form.BbsForm;
import tsuboneSystem.service.TBbsSubjectService;

public class BbsDeleteAction {

	/** TMemberのサービスクラス */
	@Resource
	protected TBbsSubjectService tBbsSubjectService;

	@ActionForm
	@Resource
	protected BbsForm bbsForm;

	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String index() {
		TBbsSubject subject = tBbsSubjectService.findById(bbsForm.id);
		subject.deleteFlag = true;
		tBbsSubjectService.update(subject);
		//return "bbsDeleteComplete.jsp";
		return "/admin/bbsList/?redirect=true";
	}
}
