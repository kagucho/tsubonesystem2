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
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;

@Component(instance = InstanceType.SESSION) 
public class MyPageForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** TMember **/
	public TMember tMember;
	
	/** tMemberClub **/
	public List<TMemberClub> tMemberClubList;
	
	/** TClub(所属している部一覧) **/
	public List<TClub> tClubList = new ArrayList<TClub>();
	
	/** TPartyList **/
	public List<TParty> tPartyList = new ArrayList<TParty>();
	
	/** TPartyList 出欠を出していない会議一覧 **/
	public List<TParty> tPartyNoAttendList;
	
	/** TPartyList 今日開催されている会議一覧 **/
	public List<TParty> tPartyToDayList;
	
	/** TMail 最近配信されたメール一覧 **/
	public List<TMail> tMailNewList;
	
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
	}
	
}
