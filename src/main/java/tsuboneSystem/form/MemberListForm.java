package tsuboneSystem.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Msg;


@Component(instance = InstanceType.SESSION) 
public class MemberListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* 名前　*/
	public String name;
	
	/* ハンドルネーム　*/
	public String hname;
	
	/* 入学年度　*/
	@DateType(datePatternStrict="yyyy",msg=@Msg(key="errors.date", resource=true))
	public String entrance;
	
	/* OBフラグ　*/
	public String obFlag;	
	
	//現在のページ番号
	public int page;
	
	//前のページがmemberList.jspならmemberListが入る
	public String referePage;
	
	/**
	 * 検索条件をクリアする
	 */
	public void clearSearchConditon() {
		name = null;
		hname = null;
		entrance = null;
		obFlag = null;
	}
}
