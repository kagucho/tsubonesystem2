package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TTempLogin;

@Component(instance = InstanceType.SESSION)
public class SettingsEditForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 一時メンバーID */
	@Required(target = "tempMemberConfirm")
	public String userName;
	
	/** 一時メンバーpassword */
	public String password;
	
	public List<TTempLogin> tTempLoginNow = new ArrayList<TTempLogin>();
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
    }

}
