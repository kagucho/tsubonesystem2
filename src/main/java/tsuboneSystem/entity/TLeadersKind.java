package tsuboneSystem.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TLeadersKindのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_LEADERS_KIND")
public class TLeadersKind implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /* 役職の名前 */
    @Column(nullable = true, unique = false)
    public String  KindName;
      
    /* 役職の内容 */
    @Column(nullable = true, unique = false)
    public String  KindMemo;
    
    /** IdをTLeaders(officerKind)に結びつける */
    @OneToMany(mappedBy = "TLeadersKind")
    public List<TLeaders> tLeadersList;
    

}