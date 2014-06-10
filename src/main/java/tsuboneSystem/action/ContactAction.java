package tsuboneSystem.action;

import org.seasar.struts.annotation.Execute;

public class ContactAction {
	
	 @Execute(validator = false)
		public String index() {
	        return "Contact.jsp";
		}

}
