package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;

public class SettingsListAction {
	
	public String actionName = "Settings";
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
    @Execute(validator = false)
	public String index() {
	
        return "settingsList.jsp";
	}
}
