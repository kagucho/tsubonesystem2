package tsuboneSystem.form;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.code.PartyAnswerCode;
import tsuboneSystem.entity.TPartyQuestion;

@Component(instance = InstanceType.SESSION) 
public class PartyQuestionAnswerForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** partyId　*/
	public Integer partyId;
	
	/** questionId　*/
	public Integer questionId;
	
	/** answer(解答内容)　*/
	@Required(target = "answerConfirm")	
	public String answer;
			
	/** 解答をメールで送ったか、誰に送ったか */
	public Integer answerSendKind;
	
	/** 解答をメールで送ったか、誰に送ったか */
	public HashMap<String, String> answerSendKindMap;
	
	/** 解答対象質問 */
	public TPartyQuestion tPartyQuestion;

	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		answer = null;
		answerSendKind = Integer.valueOf(PartyAnswerCode.NO_SEND.getCode());
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
		
        return errors;
    }
    
}
