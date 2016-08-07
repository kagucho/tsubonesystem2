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
	
	/** 回答対象人数総数 */
	public String respondMemberCount;
	
	/** 回答総数 */
	public String totalCountNum;
	
	/** 回答対象人数総数に対する回答した割合 */
	public String resAllRate;
	
	/** 選択肢一覧 */
	public List<TEnqueteSelect> tEnqueteSelectList;
	
	/** 選択肢一覧(回答状況用) */
	public List<TEnqueteSelect> tEnqueteSelectDetailList;

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
