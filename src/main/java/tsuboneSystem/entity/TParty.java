 package tsuboneSystem.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.beans.util.Beans;

import tsuboneSystem.form.PartyForm;


/**
 * TPartyエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_PARTY")
public class TParty implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public TParty() {
	}
	
	/**
	 * 引数の情報からTPartyエンティティを作成する
	 * @param creatorId
	 * @param partyForm
	 */
	public TParty(int creatorId, PartyForm partyForm) {
		/** 入力された情報をエンティティにコピー　**/
		//例外として.excludes()内に書いてある要素は省く(コピーしない)。日時関係はyyyy/mm/dd hh:mm:ssの形にしてTimestamp型に変化する必要がある。
		Beans.copy(partyForm, this).excludes("meetingDay","meetingEndDay","meetingTime","meetingDeadlineDay").execute();
		
		//編集者のIDを入れる
		this.creatorId = Integer.valueOf(creatorId);
		
		//日付と日時をString型からDate型に変換
		try {
			if (StringUtils.isNotEmpty(partyForm.meetingDay.trim())) {
				meetingDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDay);
			}
			if (StringUtils.isNotEmpty(partyForm.meetingEndDay.trim())) {
				meetingEndDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingEndDay);
			}
			if (StringUtils.isNotEmpty(partyForm.meetingTime.trim())) {
				meetingTime = new SimpleDateFormat("HH:mm").parse(partyForm.meetingTime);
			}
			if (StringUtils.isNotEmpty(partyForm.meetingDeadlineDay.trim())) {
				meetingDeadlineDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDeadlineDay);
			}
		} catch (ParseException e) {
			//起こりえない
			e.printStackTrace();
		}
		deleteFlag = Boolean.valueOf(false);
	}
	
	
	/** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** 会議の登録者Id　*/
    @Column(nullable = false, unique = false)
	public Integer creatorId;
    
    /** 会議名　*/
    @Column(nullable = false, unique = false)
	public String meetingName;
	
	/** 会議の必須判定　*/
    @Column(nullable = false, unique = false)
	public Boolean meetingNecessaryFlag;
	
	/** 会議日　*/
    @Column()
    @Temporal(TemporalType.DATE)
	public Date meetingDay;
    
    /** 会議日(終了日)　*/
    @Column()
    @Temporal(TemporalType.DATE)
	public Date meetingEndDay;
    
    /** 会議時間　*/
    @Column()
    @Temporal(TemporalType.TIME)
	public Date meetingTime;
	
	/** 会議場所　*/
    @Column(nullable = false, unique = false)
	public String meetingRoom;
	
	/** 会議内容　*/
    @Column(nullable = false, unique = false, columnDefinition ="mediumtext")
	public String meetingMemo;
	
	/** 会議出欠席締め切り日　*/
    @Column()
    @Temporal(TemporalType.DATE)
	public Date meetingDeadlineDay;
    
    /** OB出席フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  ObAttendFlag;
    
    /** 会議結果　*/
    @Column(columnDefinition ="mediumtext")
	public String meetingResult;
    
    /** 公募出欠可否 */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  noPublicFlag;
    
    /** 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  deleteFlag;
    
    /** 最終更新日　*/
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
	public Timestamp updateTime;
    
    /* memberIdをTPatyAttendに結びつける */
    @ManyToOne
    @JoinColumn(name = "CREATOR_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    /* IdをTPatyClubに結びつける */
    @OneToMany(mappedBy = "TParty")
    public List<TPartyClub> tPartyClubList;
    
    /* IdをTPartySendMailに結びつける */
    @OneToMany(mappedBy = "TParty")
    public List<TPartySendMail> tPartySendMailList;
    
    /* IdをTPartySendMailに結びつける */
    @OneToMany(mappedBy = "TParty")
    public List<TPartyAttend> tPartyAttendList;
    
    /* IdをTPartyQuestionに結びつける */
    @OneToMany(mappedBy = "TParty")
    public List<TPartyQuestion> tPartyQuestionList;
    
}