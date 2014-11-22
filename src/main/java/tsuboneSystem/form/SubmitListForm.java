package tsuboneSystem.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TSubmit;

@Component(instance = InstanceType.SESSION) 
public class SubmitListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public List<TSubmit> list;
	
	 /** 作品分類タグ選択肢 */
    public Map<Integer, String> submitTagNameMap = new HashMap<Integer, String>();
    
    /** おしらせ */
    public Map<Integer, String> topAnnounceMap = new HashMap<Integer, String>();
}
