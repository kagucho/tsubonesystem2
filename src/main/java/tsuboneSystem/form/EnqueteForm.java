package tsuboneSystem.form;

import java.io.Serializable;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class EnqueteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/** id　*/
	public Integer id;

	/** タイトル　*/
	public String title;

	/** メモ　*/
	public String memo;

	/** 選択肢　*/
	public String [] selectedContents;

	/** ユーザー名 */
	public String userName;

	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		selectedContents = null;
	}

	//オリジナルチェック
	public ActionMessages validateBase() {

		ActionMessages errors = new ActionMessages();

		return errors;
	}

}
