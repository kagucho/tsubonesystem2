package tsuboneSystem.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
	public String meetingNecessaryFlag;
	
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
	
	/* 会議出欠席締め切り時間　*/
	public String meetingDeadlineTime;
	
	/* 出席してる人のリスト　*/
	public List<TMember> tMemberOn = new ArrayList<TMember>();
	
	/* 欠席してる人のリスト　*/
	public List<TMember> tMemberOff = new ArrayList<TMember>();

	/* 未提出　*/
	public List<TMember> tMemberKuzu = new ArrayList<TMember>();
	
	/* 出欠席を返さないゴミのmap　*/
	public Map<String, String> mapKuzuSS;
	

	public boolean deadFlag(TParty tParty,Date dateNow) {
		
		if (tParty.meetingDeadlineDay.after(dateNow)) {
			return false;
		}else{
			return true;
		}
	}
	
}
