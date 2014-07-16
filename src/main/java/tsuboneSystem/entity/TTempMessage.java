package tsuboneSystem.entity;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TMemberエンティティクラス
 * 
 * @author namiyama
 */
@Entity
@Table(name = "T_TEMP_MESSAGE")
public class TTempMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /* 対象者 */
    @Column(nullable = true, unique = false)
    public Integer targetMemberId;
    
    /* メッセージ */
    @Column(nullable = true, unique = false, columnDefinition ="mediumtext")
    public String  message;
    
    /* 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  deleteFlag;
    
    /* 登録日時 */
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
	public Timestamp registTime;
}