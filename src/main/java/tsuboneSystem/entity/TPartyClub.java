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
import javax.persistence.Table;

/**
 * TPartyClubのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_PARTY_CLUB")
public class TPartyClub implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** PartyのID */
    @Column()
    public Integer  PartyId;
    
    /** MemberのID */
    @Column()
    public Integer  ClubId;
    
    /** IDをTParty(ID)に関連付ける☆ */
    @ManyToOne
    @JoinColumn(name = "PARTY_ID", referencedColumnName = "ID")
    public TParty tParty;
    
    /** メンバーのIDをTMember(ID)に関連付ける★ */
    @ManyToOne
    @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID")
    public TClub tClub;

}