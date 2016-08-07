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

import java.util.ArrayList;
import java.util.List;

import tsuboneSystem.entity.TMember;

public class ExcelDto  {
	
	private String hname;
		
	private String meetingName;
	
	private List<TMember> memberList = new ArrayList<TMember>();
	
	//ゲッター
	public String getHname(){
		return hname;
	}
	
	public String getMeetingName(){
		return meetingName;
	}
	
	public List<TMember> getMemberList() {
		return memberList;
	}
	
	//セッター
	public void setHname(String hname) {
		this.hname = hname;
	}
	
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public void setMemberList(List<TMember> memberList) {
		this.memberList = memberList;
	}
	
}
