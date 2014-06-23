package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;

@Component(instance = InstanceType.SESSION) 
public class PartyAttendForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* 編集画面において選択肢のボタンの名称　*/
	public String btn1;
	
	/* 編集画面において選択肢のボタンのKey　*/
	public String btn1Key;
	
	/* 編集画面において選択肢のボタンの名称　*/
	public String btn2;
	
	/* 編集画面において選択肢のボタンのKey　*/
	public String btn2Key;
	
	/* メールを送信したメンバーのID　*/
	public Integer registMemberId;
	
	/* メールのタイトル　*/
	public String title;
	
	/* メールの内容　*/
	public String content;
	
	/* メンバー情報　*/
	public TMember tMember;
	
	/* 会議情報　*/
	public TParty tParty;
	
	/* 出席してる人のリスト　*/
	public List<TPartyAttend> tAttendOn;
	
	/* 欠席してる人のリスト　*/
	public List<TPartyAttend> tAttendOff;
	
	/* 出欠席を返さないゴミのリスト　*/
	public List<TPartyAttend> tAttendKuzu;
	
	/* 出欠席を返さないゴミのmap(表示するために使用)　*/
	public Map<String, String> mapKuzuSS;
	
	/* 出席してる人のリスト　*/
	public List<TMember> tMemberOn = new ArrayList<TMember>();
	
	/* 欠席してる人のリスト　*/
	public List<TMember> tMemberOff = new ArrayList<TMember>();
	
	/* 出欠席を返さないゴミのリスト　*/
	public List<TMember> tMemberKuzu = new ArrayList<TMember>();;
	
	/* 更新対象メンバー　*/
	public List<TMember> tMemberNew = new ArrayList<TMember>();
	
	/* メール送信対象メンバー　*/
	public List<TMember> tMemberSendList = new ArrayList<TMember>();
	
	/* 更新対象　*/
	public List<TPartyAttend> tPartyAttendNew = new ArrayList<TPartyAttend>();
	
	/* 更新対象　*/
	public List<TPartyAttend> tPartyAttendNewNo = new ArrayList<TPartyAttend>();
	
	/* 出席している人を移動させるための配列　*/
	public String[] onCheck = new String[10];
	
	/* 欠席している人を移動させるための配列　*/
	public String[] offCheck = new String[10];
	
	/* 出欠席をしていない人を移動させるための配列　*/
	public String[] kuzuCheck = new String[10];

	
}
