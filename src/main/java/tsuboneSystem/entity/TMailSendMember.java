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
 * TMemberエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_MAIL_SEND_MEMBER")
public class TMailSendMember implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /* MailID */
    @Column(nullable = false, unique = false)
    public Integer  mailId;
    
    /* MemberID */
    @Column(nullable = false, unique = false)
    public Integer  memberId;
    
    /** TMailSendMember(mailId) = TMail(id) */
    @ManyToOne
    @JoinColumn(name = "MAIL_ID", referencedColumnName = "ID")
    public TMail tMail;
    
    /** TMailSendMember(memberId) = TMember(id) */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

}