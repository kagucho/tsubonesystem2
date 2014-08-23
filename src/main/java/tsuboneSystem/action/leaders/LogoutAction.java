package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;

public class LogoutAction {
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	@Resource
	protected LoginAdminDto loginAdminDto;

	
	@Execute(validator = false)
	@RemoveSession(name = "loginAdminDto")
	public String index() {
        return "/login/?redirect=true";
	}
}
