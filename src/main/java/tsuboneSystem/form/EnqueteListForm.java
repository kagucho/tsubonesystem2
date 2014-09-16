package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TEnquete;

@Component(instance = InstanceType.SESSION) 
public class EnqueteListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public List<TEnquete> list;
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
       
		
        return errors;
    }
	
}
