 package tsuboneSystem.entity;


import java.io.Serializable;
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


/**
 * TPartyエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_PARTY")
public class TParty implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
    
    /** 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  deleteFlag;
    
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
    
}