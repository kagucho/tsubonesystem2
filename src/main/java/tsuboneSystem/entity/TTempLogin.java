package tsuboneSystem.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTempLoginエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_TEMP_LOGIN")
public class TTempLogin implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Integer id;
    
    /* ID */
    @Column(nullable = false, unique = true)
    public String userName;
    
    /* パスワード */
    @Column(nullable = false, unique = false)
    public String password;
    
    /* 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  deleteFlag;


}