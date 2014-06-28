package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;

@Component(instance = InstanceType.SESSION)
public class ClubForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Integer id;
	
	/* 部名　*/
	@Required
	@Maxlength(maxlength=10)
	public String ClubName;
	
	/* 部長のID　*/
	@Required
	public String OfficerId;
	
	/* 部の概要　*/
	@Required
	public String ClubMemo;
	
	/* 削除フラグ　*/
	public String deleteFlag;
	
	/* 部長　*/
	public TLeaders tLeaders;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	/** Clubのマップ */
	public List<Map<Integer,String>> clubMap;	
	
	/** memberのマップ */
	public Map<String,String> memberMap;
	
	/** memberのマップ(Integer String) */
	public Map<Integer,String> memberMapIS;
	
	/** 選択した項目のパラメータ　*/
	public String[] club_checks = new String[0];
	
	/** 部の代表者の情報を格納する　*/
	public TMember tMember;
	
	/** 部に所属している人のリスト　*/
	public List<TMemberClub> tMemberClubList;
	
	/** 部に所属している人のリスト*/
	public List<TMember> tMemberList;
	
	/** 全メンバーのリスト　*/
	public List<TMember> tMemberAllList;
	
	//以下メール送信関係
		/** メールの送信相手のリスト　*/
		public List<TMember> tMemberSendList;
		
		/* メールの送信者のID　*/
		public Integer registMemberId;
		
		/* メールのタイトル　*/
		@Required(target = "confirmMail")
		public String title;

		/* メールの内容　*/
		@Required(target = "confirmMail")
		public String content;
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		ClubName = null;
		deleteFlag = null;
		OfficerId = null;
		ClubMemo = null;
		tLeaders =null;
    }

}
