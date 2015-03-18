package tsuboneSystem.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clubのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_CLUB")
public class TClub implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** 部名 */
    @Column(nullable = true, unique = false)
    public String  ClubName;
    
    /** 部長のID */
    @Column(nullable = true, unique = false)
    public Integer  LeadersId;
    
    /** 部の説明 */
    @Column(nullable = false, unique = false)
    public String  ClubMemo;
    
    /** 部 個別のホームページ */
    @Column()
    public String  clubUrl;
    
    /** 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  deleteFlag;
    
    /** 部長のIDをTClubに関連付ける */
    @OneToOne
    @JoinColumn(name = "LEADERS_ID", referencedColumnName = "ID")
    public TLeaders tLeaders;
    
    /** IdをTPartyClubに結びつける */
    @OneToMany(mappedBy = "tClub")
    public List<TPartyClub> tPartyClubList;
    
    /** IdをTMemberClubに結びつける */
    @OneToMany(mappedBy = "tClub")
    public List<TMemberClub> tMemberClubList;

}