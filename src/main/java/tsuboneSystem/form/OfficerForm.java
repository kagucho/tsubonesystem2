package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;

@Component(instance = InstanceType.SESSION)
public class OfficerForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Integer id;
	
	/* 役職種 */
	public String OfficerKind;
	
	/* MemberId */
	public String MemberId;
		
	/* Clubのリスト */
	public List<TClub> clubItems;
	
	/* Memberのリスト */
	public List<TMember> memberItems;
	
	/* 表示するためのリスト */
	public List<TClub> officerListItem = new ArrayList<TClub>();
	
	/* TLeaderテーブルの一覧を一回格納するためのリスト　*/
	public List<TLeaders> leaders;
	
	/* 選択されたレコードを格納しておくTLeader　*/
	public TLeaders leadersUp;
	
	/* 更新の時に選択されたメンバーを入れておく　*/
	public TMember tMemberNew;
	
	/* 局長の情報を格納する　*/
	public List<TLeaders> tLeadersChief = new ArrayList<TLeaders>();
	
	/* 副局長の情報を格納する　*/
	public List<TLeaders> tLeadersSubChief = new ArrayList<TLeaders>();
	
	/* 会計の情報を格納する　*/
	public List<TLeaders> tLeadersAccount = new ArrayList<TLeaders>();
	
	/* 合宿委員の情報を格納する　*/
	public List<TLeaders> tLeadersGassyuku = new ArrayList<TLeaders>();
	
	/* 合宿委員の情報を格納する　*/
	public List<TLeaders> tLeadersWebAdmin = new ArrayList<TLeaders>();

}
