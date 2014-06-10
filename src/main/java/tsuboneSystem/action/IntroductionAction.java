
package tsuboneSystem.action;

import org.seasar.struts.annotation.Execute;

public class IntroductionAction {
	
    @Execute(validator = false)
	public String index() {
        return "Introduction.jsp";
	}
}
