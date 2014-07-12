package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TBbsDetail;
import tsuboneSystem.entity.TBbsSubject;

@Component(instance = InstanceType.SESSION) 
public class BbsForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** スレッド名 */
	@Required(target = "bbsRegist")
	public String title;
	
	/** 内容 */
	@Required(target = "bbsDetailRegist")
    public String detail;
    
    /* スレッド一覧 */
    public List<TBbsSubject> tBbsSubjectList;
    
    /* 各スレッドに投稿された内容一覧 */
    public List<TBbsDetail> tBbsDetailList;
    
  //リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
  	public void resetInput() {
  		id = null;
  		title = null;
  		detail = null;
  	}
		
}
