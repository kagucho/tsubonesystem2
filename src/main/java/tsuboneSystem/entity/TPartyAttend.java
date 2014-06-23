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
 * TPartyAttendのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_PARTY_ATTEND")
public class TPartyAttend implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** partyId */
    @Column(nullable = true, unique = false)
    public Integer  partyId;
      
    /** memberId */
    @Column(nullable = true, unique = false)
    public Integer  memberId;
    
    /** 出欠席状況 */
    @Column(nullable = true, unique = false)
    public Integer attend;
    
    @ManyToOne
    @JoinColumn(name = "PARTY_ID", referencedColumnName = "ID")
    public TParty tParty;
    
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    

}