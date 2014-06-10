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
    public Integer  PartyId;
    
    /** MemberのID */
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