package tsuboneSystem.action;

import org.seasar.struts.annotation.Execute;

public class ClubInfoAction {
	
	 @Execute(validator = false)
		public String index() {
	        return "ClubInfo.jsp";
		}

}
