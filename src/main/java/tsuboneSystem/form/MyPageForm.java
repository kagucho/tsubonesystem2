package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;

@Component(instance = InstanceType.SESSION) 
public class MyPageForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** TMember **/
	public TMember tMember;
	
	/** tMemberClub **/
	public List<TMemberClub> tMemberClubList;
	
	/** TClub(所属している部一覧) **/
	public List<TClub> tClubList = new ArrayList<TClub>();
	
	/** TPartyList **/
	public List<TParty> tPartyList = new ArrayList<TParty>();
	
	/** TPartyList 出欠を出していない会議一覧 **/
	public List<TParty> tPartyNoAttendList;
	
	/** TPartyList 今日開催されている会議一覧 **/
	public List<TParty> tPartyToDayList;
	
	/** TMail 最近配信されたメール一覧 **/
	public List<TMail> tMailNewList;
	
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
	}
	
}
