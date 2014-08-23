package tsuboneSystem.action.tempRegist;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.MyPageForm;

public class IndexAction {
	
	public String actionName = "Welcome!!";
	
	public String actionNameSub = null;
	
	/** Indexのアクションフォーム */
	@ActionForm
	@Resource
	protected MyPageForm myPageForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	
    @Execute(validator = false)
	public String index() {
    	
    	return "index.jsp";
	}
}
