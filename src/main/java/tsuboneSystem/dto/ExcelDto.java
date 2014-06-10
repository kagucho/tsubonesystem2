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
