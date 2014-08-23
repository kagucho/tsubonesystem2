package tsuboneSystem.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TTempLogin;


@Component(instance = InstanceType.SESSION)
public class LoginTempLoginDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/** ログインID **/
	public  Integer memberId;
	
	/** ログインしている人の情報 **/
	public TTempLogin tMemberLogin;
}
