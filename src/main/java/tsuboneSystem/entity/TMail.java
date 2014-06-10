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
 * @author oowada
 */
@Entity
@Table(name = "T_MAIL")
public class TMail implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /* メールの作成者 */
    @Column(nullable = true, unique = false)
    public Integer  registMemberId;
    
    /* メールのタイトル */
    @Column(nullable = true, unique = false)
    public String  title;
    
    /* メールの内容 */
    @Column(nullable = true, unique = false, columnDefinition ="mediumtext")
    public String  content;
    
    /** TMail(ID) = TMailSendMember(mailId) */
    @OneToMany(mappedBy = "TMail")
    public List<TMailSendMember> tMailSendMember;
    
    /** TMail(registMemberId) = TMember(id) */
    @ManyToOne
    @JoinColumn(name = "REGIST_MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

}