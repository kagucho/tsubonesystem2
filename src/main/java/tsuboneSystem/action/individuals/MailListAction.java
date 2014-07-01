package tsuboneSystem.action.individuals;

import org.seasar.struts.annotation.Execute;

public class MailListAction extends tsuboneSystem.action.admin.MailListAction{
	@Override
	@Execute(validator = false)
	public String index() {
		return "index.jsp";
	}
}
