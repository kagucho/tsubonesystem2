package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TTopAnnounce;

@Component(instance = InstanceType.SESSION) 
public class TopAnnounceListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 一覧 */
	public List<TTopAnnounce> list;
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
	}
}
