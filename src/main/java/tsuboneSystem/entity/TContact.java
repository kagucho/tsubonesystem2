package tsuboneSystem.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TContactエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_CONTACT")
public class TContact implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** 名前 */
    @Column()
    public String name;
    
    /** メールアドレス */
    @Column()
    public String mail;
    
    /** 題名 */
    @Column()
    public String subject;
    
    /** メッセージ */
    @Column()
    public String message;


}