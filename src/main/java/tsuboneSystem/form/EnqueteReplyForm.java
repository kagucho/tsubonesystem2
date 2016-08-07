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
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TEnqueteAnswer;

@Component(instance = InstanceType.SESSION)
public class EnqueteReplyForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/** id　*/
	public Integer id;

	/** 選択肢のリスト */
	public Map<String, String> enqueteSelectMap;

	/** 回答者のID */
	public Integer memberId;

	/** 選択された選択肢 */
	public String answer;
	
	public TEnqueteAnswer tEnqueteAnswerOld;

	public boolean isOld;

	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		answer = null;
		memberId = null;
		enqueteSelectMap = null;
	}

	//オリジナルチェック
	public ActionMessages validateBase() {

		ActionMessages errors = new ActionMessages();

		//選択されたMemberが連絡先をすべて登録しているかを確認する。
		if (StringUtils.isEmpty(answer)) {
			errors.add("answer", new ActionMessage("いずれかの選択肢を選択してください。", false));
		}

		return errors;
	}
}
