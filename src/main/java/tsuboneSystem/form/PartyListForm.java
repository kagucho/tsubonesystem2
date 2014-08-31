package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TParty;

@Component(instance = InstanceType.SESSION) 
public class PartyListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** 期限付きで出欠登録中の会議一覧 */
	public List<TParty> tPartyAttendNow;
	
	/** 期限なしで出欠登録中の会議一覧 */
	public List<TParty> tPartyNoAttendNow;
	
	/** 開催待ちの会議一覧 */
	public List<TParty> tPartySessionWill;
	
	/** 開催中の会議一覧 */
	public List<TParty> tPartySessionNow;
	
	/** 構想中の会議一覧 */
	public List<TParty> tPartyIdea;
	
	/** 開催が終了した会議 */
	public List<TParty> tPartyHistory;
	
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
        return errors;
    }
	
}
