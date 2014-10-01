package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.LoginMemberDto;

public class LogoutAction {
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;

	
	@Execute(validator = false)
	@RemoveSession(name = {"loginIndividualsDto", "loginMemberDto"})
	public String index() {
        return "/login/?redirect=true";
	}
}
