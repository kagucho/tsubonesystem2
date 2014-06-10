package tsuboneSystem.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

@Component(instance = InstanceType.SESSION) 
public class LoginForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	@Required(msg=@Msg(key="errors.id", resource=true))
	public String id;
	
	/* 会議名　*/
	@Required(msg=@Msg(key="errors.password", resource=true))
	public String password;
	
}
