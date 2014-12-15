package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TEnqueteSelect;

@Component(instance = InstanceType.SESSION)
public class EnqueteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/** id　*/
	public Integer id;

	/** タイトル　*/
	@Required
	public String title;

	/** メモ　*/
	@Required
	public String memo;

	/** 選択肢　*/
	@Required
	public String [] selectedContents;

	/** ユーザー名 */
	public String userName;

	public List<TEnqueteSelect> tEnqueteSelectList;

	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		selectedContents = null;
		title = null;
		memo = null;
	}

	//オリジナルチェック
	public ActionMessages validateBase() {

		ActionMessages errors = new ActionMessages();
		if (selectedContents.length < 2) {
			errors.add("selectedContents", new ActionMessage("選択肢が作られていません。", false));
		}
		return errors;
	}

}
