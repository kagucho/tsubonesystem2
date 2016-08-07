/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
