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

package tsuboneSystem.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;


@Component(instance = InstanceType.SESSION)
public class PartyDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* 会議名　*/
	public String meetingName;
	
	/* 会議の必須判定　*/
	public boolean meetingNecessaryFlag;
	
	/* 会議日時　*/
	public String meetingDay;
	
	/* 会議時間　*/
	public String meetingTime;
	
	/* 会議場所　*/	
	public String meetingRoom;
	
	/* 会議内容　*/
	public String meetingMemo;
	
	/* 出席対象者　*/
	public String[] attendClub = new String[0];
	
	/* OB出席フラグ */
    public boolean ObAttendFlag;
	
	/* 会議出欠席締め切り日　*/
	public String meetingDeadlineDay;
	
	/* 出席してる人のリスト　*/
	public List<TMember> tMemberOn = new ArrayList<TMember>();
	
	/* 欠席してる人のリスト　*/
	public List<TMember> tMemberOff = new ArrayList<TMember>();

	/* 未提出　*/
	public List<TMember> tMemberKuzu = new ArrayList<TMember>();
	
	/* 出欠席を返さないゴミのmap　*/
	public Map<String, String> mapKuzuSS;
	
	/* 会議の登録者 */
	public TMember tMember;
	
	/* 会議の制作者がログイン者の場合true(一般メンバーの編集権限判定に使用) */
	public boolean myPartyFlag = false;
	

	public boolean deadFlag(TParty tParty,Date dateNow) {
		
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(dateNow);
		calendar.add(Calendar.DATE, -1);
		Date dateadd = new Date();
		dateadd=calendar.getTime();
		
		if(tParty.meetingDeadlineDay != null){
			//開催日がなく、締め切りのみのとき
			if (tParty.meetingDeadlineDay.after(dateadd)) {
				return false;
			}else{
				return true;
			}
		} else if (tParty.meetingEndDay != null){
			//最終日が定められているとき
			if (tParty.meetingEndDay.after(dateadd)) {
				return false;
			}else{
				return true;
			}
		} else if (tParty.meetingDay != null){
			//開始日が定められているとき
			if (tParty.meetingDay.after(dateadd)) {
				return false;
			}else{
				return true;
			}
		} else {
			//開催日も締め切り日も設定されていない会議は編集されてから一ヶ月で締め切りとする
			calendar.add(Calendar.MONTH, -1);
			Date dateaddMonth = new Date();
			dateaddMonth=calendar.getTime();
			
			if (tParty.updateTime.after(dateaddMonth)) {
				return false;
			}else{
				return true;
			}
		}
	}
}
