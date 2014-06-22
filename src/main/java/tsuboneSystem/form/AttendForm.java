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
	

	

	
}
