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
