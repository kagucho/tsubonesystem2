package tsuboneSystem.dto;

import java.io.Serializable;
import java.util.Date;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TParty;
import tsuboneSystem.original.util.TsuboneSystemUtil;


/**
 * カレンダーjson用Dto
 * 
 * */
@Component(instance = InstanceType.SESSION)
public class FullCalenderDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 予定 名前
	public String id;
	
	// 予定 名前
	public String title;
	
	// 予定 開始
	public String start;
	
	// 予定 終了
	public String end;
	
	/**
	 * TPartyからカレンダー用のDTOを作成
	 * @param tParty
	 * 
	 * */
	public FullCalenderDto(TParty tParty) {
		this.id = tParty.id.toString();
		this.title = tParty.meetingName;
		this.start = createStringFromDateForCalender(tParty.meetingDay, tParty.meetingTime);
		this.end = createStringFromDateForCalender(tParty.meetingEndDay, null);
	}
	
	/**
	 * Dateの日付とDateの時間からカレンダー用の日付をStringで生成。
	 * 時間が設定されていない場合は午前五時が設定される
	 * @param Date(日付)
	 * @param Date(時間)
	 * 
	 * */
	private String createStringFromDateForCalender (Date date, Date time) {
		StringBuilder bu = new StringBuilder();
		if (date != null) {
			bu.append(TsuboneSystemUtil.parsDateToString(date));
		}
		if (time != null) {
			bu.append("T");
			bu.append(TsuboneSystemUtil.parsTimeToString(time));
			bu.append("-05:00");
		}
		return new String(bu);
	}
}
