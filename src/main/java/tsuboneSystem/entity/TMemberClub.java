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
 * MemberClubのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_MEMBER_CLUB")
public class TMemberClub implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** メンバーのID */
    public Integer  MemberId;
    
    /** 所属している部のID */
    public Integer  ClubId;
    
    /** 所属している部のIDをTClub(ID)に関連付ける☆ */
    @ManyToOne
    @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID")
    public TClub tClub;
    
    /** メンバーのIDをTMember(ID)に関連付ける★ */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

}