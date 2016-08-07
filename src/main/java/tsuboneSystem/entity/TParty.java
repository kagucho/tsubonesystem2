/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import javax.persistence.Transient;

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
	 * @param partyFormEnt
	 */
	public TParty(int creatorId, PartyForm partyFormEnt) {
		/** 入力された情報をエンティティにコピー　**/
		//例外として.excludes()内に書いてある要素は省く(コピーしない)。日時関係はyyyy/mm/dd hh:mm:ssの形にしてTimestamp型に変化する必要がある。
		Beans.copy(partyFormEnt, this).excludes("meetingDay","meetingEndDay","meetingTime","meetingDeadlineDay").execute();
		
		//編集者のIDを入れる
		this.creatorId = Integer.valueOf(creatorId);
		
		//日付と日時をString型からDate型に変換
		try {
			if (StringUtils.isNotEmpty(partyFormEnt.meetingDay)) {
				meetingDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyFormEnt.meetingDay);
			}
			if (StringUtils.isNotEmpty(partyFormEnt.meetingEndDay)) {
				meetingEndDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyFormEnt.meetingEndDay);
			}
			if (StringUtils.isNotEmpty(partyFormEnt.meetingTime)) {
				meetingTime = new SimpleDateFormat("HH:mm").parse(partyFormEnt.meetingTime);
			}
			if (StringUtils.isNotEmpty(partyFormEnt.meetingDeadlineDay)) {
				meetingDeadlineDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyFormEnt.meetingDeadlineDay);
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
    
    /** 会議時間ss　*/
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
    
    /** 会議結果の最終編集者　*/
    @Column()
	public Integer resultEditMemberId;
    
    /** 最終編集フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  resultEditEndFlag;
    
    /** 公募出欠可否 */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  noPublicFlag;
    
    /** 人数通知設定値 */
    @Column()
    public Integer noticeMemberNum;
    
    /** 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  deleteFlag;
    
    /** 締め切りまでの日数(このカラムは生成されない) */
    @Transient
    public Integer deadlineHowNum;
    
    /** 最終更新日　*/
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
	public Timestamp updateTime;
    
    /* memberIdをTPatyAttendに結びつける */
    @ManyToOne
    @JoinColumn(name = "CREATOR_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    /* memberIdをTPatyAttendに結びつける */
    @ManyToOne
    @JoinColumn(name = "RESULT_EDIT_MEMBER_ID", referencedColumnName = "ID")
    public TMember editTMember;
    
    /* IdをTPatyClubに結びつける */
    @OneToMany(mappedBy = "tParty")
    public List<TPartyClub> tPartyClubList;
    
    /* IdをTPartySendMailに結びつける */
    @OneToMany(mappedBy = "tParty")
    public List<TPartySendMail> tPartySendMailList;
    
    /* IdをTPartySendMailに結びつける */
    @OneToMany(mappedBy = "tParty")
    public List<TPartyAttend> tPartyAttendList;
    
    /* IdをTPartyQuestionに結びつける */
    @OneToMany(mappedBy = "TParty")
    public List<TPartyQuestion> tPartyQuestionList;
}