package tsuboneSystem.entity;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBbsDetailのエンティティクラス
 * 掲示番の各スレッドの内容
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_BBS_DETAIL")
public class TBbsDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** TBbsSubjectのID */
    @Column()
    public Integer subjectId;
    
    /** 内容 */
    @Column(columnDefinition ="mediumtext")
    public String detail;

    /** 登録者Id */
    @Column()
    public Integer memberId;
    
    /** メール送信Id */
    @Column()
    public Integer mailId;
    
    /** 最終更新日　*/
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
	public Timestamp updateTime;
    
    /** 削除フラグ　*/
    @Column(columnDefinition ="boolean default '0'")
    public boolean  deleteFlag;
    
    /* memberIdをTPatyAttendに結びつける */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    /* subjectIdをTPatyAttendに結びつける */
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID")
    public TBbsSubject tBbsSubject;
    
    /* メールのIDをTMail(ID)に関連付ける */
    @OneToOne(mappedBy = "tBbsDetail")
    public TMail tMail;

}