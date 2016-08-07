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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clubのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_LEADERS")
public class TLeaders implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /* 役職の種類 */
    @Column(nullable = true, unique = false)
    public Integer  OfficerKind;
      
    /* 部長のMemberID */
    @Column(nullable = true, unique = false)
    public Integer  MemberId;
    
    /* メンバーの秘匿情報の表示可否 */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  secretInformation;
    
    /* メンバー編集権限可否 */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  memberUpdate;
    
    /* 他人の出欠管理可否 */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  attendUpdate;
       
    
    /** MemberidをTMember(ID)に関連付ける */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    /** 部長のIDをTClubに関連付ける */
    @OneToOne(mappedBy = "tLeaders")
    public TClub tClub;
    

}