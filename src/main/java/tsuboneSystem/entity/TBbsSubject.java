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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBbsSubjectのエンティティクラス
 * 掲示番のスレッド名 
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_BBS_SUBJECT")
public class TBbsSubject implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** スレッド名 */
    @Column()
    public String title;
    
    /** 登録者Id */
    @Column()
    public Integer memberId;
    
    /** メール送信Id */
    @Column()
    public Integer mailId;
    
    /** 最終更新日　*/
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
	public Timestamp updateTime;
    
    /** 削除フラグ　*/
    @Column(columnDefinition ="boolean default '0'")
    public boolean  deleteFlag;
    
    /* IdをTBbsDetailに結びつける */
    @OneToMany(mappedBy = "tBbsSubject")
    public List<TBbsDetail> tBbsDetailList;
    

    /* memberIdをTPatyAttendに結びつける */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

    /* メールのIDをTMail(ID)に関連付ける */
    @OneToOne(mappedBy = "tBbsSubject")
    public TMail tMail;
}