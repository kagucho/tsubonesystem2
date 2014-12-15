package tsuboneSystem.form;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
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

	/** 選択された選択肢 */
	public String answer;

	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		answer = null;
		memberId = null;
		enqueteSelectMap = null;
	}


	//オリジナルチェック
    public ActionMessages validateBase(){

        ActionMessages errors = new ActionMessages();

      //選択されたMemberが連絡先をすべて登録しているかを確認する。
    	if (StringUtils.isEmpty(answer)) {
    		errors.add("answer",new ActionMessage("いずれかの選択肢を選択してください。",false));
    	}


        return errors;
    }
}
