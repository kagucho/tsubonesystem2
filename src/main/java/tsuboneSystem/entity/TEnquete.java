package tsuboneSystem.entity;
import java.io.Serializable;
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

/**
 * TMemberエンティティクラス
 * 
 * @author ryuya
 */
@Entity
@Table(name = "T_ENQUETE")
public class TEnquete implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Integer id;
    
    /* タイトル */
    @Column(nullable = true, columnDefinition ="mediumtext")
    public String  title;
    
    /* 内容 */
    @Column(nullable = false)
    public String  memo;
    
    /* 製作者 */
    @Column(nullable = false)
    public Integer createId;
    
    /** createIDをTMember(ID)に関連付ける */
   
    @Column(nullable = false)
    public Integer memberId;
    
    /** createIDをTMember(ID)に関連付ける */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    /** EnqueteSelectをTEnquete(ID)に関連付ける */
    @OneToMany(mappedBy = "tEnquete")
    public List<TEnqueteSelect> tEnqueteSelect;
    
}