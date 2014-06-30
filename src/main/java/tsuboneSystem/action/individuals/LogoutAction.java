package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;

public class LogoutAction {
	
	
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;

	
	@Execute(validator = false)
	@RemoveSession(name = "loginIndividualsDto")
	public String index() {
        return "/login/?redirect=true";
	}
}
