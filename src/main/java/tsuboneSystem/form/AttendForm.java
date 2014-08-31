package tsuboneSystem.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION) 
public class AttendForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* 出欠席　*/
	public Integer attendFlag;
	
	/* メッセージ　*/
	public String meetingName;
	
	/* メッセージ　*/
	public String attendMessege;
	
	/** 一覧より、出席が押された会議のid */
	public Integer yesId;
	
	/** 一覧より、欠席が押された会議のid */
	public Integer noId;

	
}
