package tsuboneSystem.form;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.entity.TTopAnnounce;

@Component(instance = InstanceType.SESSION) 
public class AnnounceListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** id */
	public Integer id;
	
	/** 選択された作品の */
	public Integer tSubmitId;
	
	/** トップアナウンス情報 */
	public TTopAnnounce tTopAnnounce;
	
	/** 作品一覧 */
	public List<TSubmit> tSubmitList;
	
	/** 作品分類タグ選択肢 */
    public Map<String, String> submitTagNameMap = new HashMap<String, String>();
}
