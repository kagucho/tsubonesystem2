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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION) 
public class MailForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/** 現役生かOBか 1:現役生　2:OB */
	public String activeOrOb;
	
	/** 全員か部ごとか 1:全員 2:部ごと */
	public String allOrClub;
	
	/* 選択した部 */
	public String[] clubListCheck = new String[0];
	
	/* メールのタイトル　*/
	@Required
	public String title;
	
	/* メールの内容　*/
	@Required
	public String content;
	
	/* メールの送信相手　*/
	public Set<Integer> MemberSendSet = new HashSet<Integer>();
	
	/* メールの送信相手　*/
	public Set<TMember> MemberSendSet2 = new HashSet<TMember>();
	
	/* メールの送信者　*/
	public Integer registMemberId;
	
	/** Member一覧　*/
	public List<TMember> tMemberItem;
	
	/** 選ばれたmember　*/
	public String[] memberSelect;
	
	/** Member一覧　*/
	public List<TMember> tMemberSendList = new ArrayList<TMember>();
	
	/** 登録されている部一覧 */
	public List<TClub> clubList;

	/** 部のマップ */
	public HashMap<String, String> clubMapSS;
	
	/** 登録されている部一覧 */
	public String getContent() {
		return content; 
	}
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		activeOrOb = null;
		allOrClub = null;
		title = null;
		content = null;
		clubListCheck = new String[0];
	}
	//オリジナルチェック
	public ActionMessages validateBase(){
		ActionMessages errors = new ActionMessages();
		
		if (StringUtil.isNotEmpty(activeOrOb)) {
			if ("1".equals(activeOrOb)) {
				// 現役生の場合は全員か部ごとか
				if (StringUtil.isNotEmpty(allOrClub)) {
					if ("2".equals(allOrClub)) {
						//　部ごとのメールの場合は部が選択されている必要がある
						if ("1".equals(activeOrOb)) {
							if (clubListCheck.length == 0) {
								errors.add("clubListCheck",new ActionMessage("部を選択してください",false));
							}
						}
					}
				} else {
					errors.add("allOrClub",new ActionMessage("送る範囲を選択してください",false));
				}
			}
		} else {
			errors.add("activeOrOb",new ActionMessage("送り相手を選択してください。",false));
		}
       
    return errors;
    }
}
