package tsuboneSystem.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

@Component(instance = InstanceType.SESSION) 
public class PasswordReissueForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** メールアドレス */
	@Required()
	public String mailAddress;
	
	/** 仮パスワード */
	public String tempPass;

}
