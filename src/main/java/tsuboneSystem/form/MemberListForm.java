package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Msg;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;


@Component(instance = InstanceType.SESSION) 
public class MemberListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* 名前　*/
	public String name;
	
	/* ハンドルネーム　*/
	public String hname;
	
	/* 入学年度　*/
	@DateType(datePatternStrict="yyyy",msg=@Msg(key="errors.date", resource=true))
	public String entrance;
	
	/* OBフラグ　*/
	public String obFlag;	
	
	/** TMember **/
	public TMember tMember;
	
	/** 部のリスト **/
	public List<TClub> clubList;
	
	/** 部のマップ **/
	public Map<Integer, String> clubMap;
	
	/** 部のマップ **/
	public Map<String, String> clubMapSS;
	
	/** 性別のマップ **/
	public Map<String, String> sexMap;
	
	/** 選択した部 **/
	public String[] clubListCheck = new String[10];
	
	/** 選択されていた部(更新時に使用) **/
	public List<String> clubListChecked = new ArrayList<String>();
	
	/** メンバーが所属している部のIDのリスト **/
	public List<TMemberClub> tMemberClubList;
	
	/** メンバーが所属している部のIDのリスト(completeで使用) **/
	public List<TMemberClub> tMemberClubUpOldId;
	
	/** ページ番号 */
	private int pages;
	
	/** 総ページ数 */
	private int[] allpages;
	public int[] getAllpages() {
		return allpages;
	}
	
	public void setAllpages(int[] allpages) {
		this.allpages = allpages;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		clubListChecked = new ArrayList<String>();
		obFlag = null;
    }
}
