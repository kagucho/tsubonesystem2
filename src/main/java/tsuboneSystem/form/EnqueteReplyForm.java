package tsuboneSystem.form;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class EnqueteReplyForm implements Serializable{

	private static final long serialVersionUID = 1L;

	/** id　*/
	public Integer id;

	/** 選択肢のリスト */
	public Map<String, String> enqueteSelectMap;

	/** 回答者のID */
	public Integer memberId;

	public String answer;


	//オリジナルチェック
    public ActionMessages validateBase(){

        ActionMessages errors = new ActionMessages();


        return errors;
    }
}
