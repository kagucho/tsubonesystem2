 package tsuboneSystem.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * TPartySettingsエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_PARTY_SETTINGS")
public class TPartySettings implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** partyId　*/
    @Column()
    public Integer partyId;
	
	/** 会議の必須判定　*/
    @Column(nullable = false, unique = false)
	public Boolean meetingNecessaryFlag;
    
    /** OB出席フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public Boolean  ObAttendFlag;
    
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
    
    /** partyId　*/
    @OneToOne
    @JoinColumn(name = "PARTY_ID", referencedColumnName = "ID")
    public TParty tParty;
    
}