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
 * TMemberエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_MEMBER")
public class TMember implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Integer id;
    
    /* 名前 */
    @Column(nullable = true, unique = false)
    public String  name;
    
    /* ハンドルネーム */
    @Column(nullable = false, unique = false)
    public String  hname;
    
    /* 性別 */
    @Column(nullable = true, unique = false)
    public String sex;
    
    /* メールアドレス */
    @Column(nullable = false, unique = false)
    public String mail;
    
    /* 学科 */
    @Column(nullable = true, unique = false)
    public String curriculum;
    
    /* 入学年度*/
    @Column(nullable = true, unique = false)
    public Integer entrance;
   
    /* 電話番号1 */
    @Column(nullable = true, unique = false)
    public String tel1;
    
    /* 電話番号2 */
    @Column(nullable = true, unique = false)
    public String tel2;
    
    /* 電話番号3 */
    @Column(nullable = true, unique = false)
    public String tel3;
    
    /* ID */
    @Column(nullable = false, unique = true)
    public String userName;
    
    /* パスワード */
    @Column(nullable = false, unique = false)
    public String password;
    
    /* OBフラグ */
    @Column(nullable = true, unique = false)
    public boolean obFlag;
    
    /* メール不達フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  sendErrorFlag;
    
    /* 削除フラグ */
    @Column(columnDefinition ="boolean default '0'")
    public boolean  deleteFlag;
    
    
    /** memberIdをTLeadersに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TLeaders> tLeadersList;
    
    /** memberIdをTPatyAttendに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TPartyAttend> tPaertAttendList;
    
    /** memberIdをTPatyAttendに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TParty> tPaertyList;
    
    /** memberIdをTMailSendMemberに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TMailSendMember> tMailSendMemberList;
    
    /** memberIdをTMailに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TMail> tMailList;
    
    /** memberIdをTPartyQuestionに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TPartyQuestion> tPartyQuestionList;
    
    /** memberIdをTPartyAnswerに結びつける */
    @OneToMany(mappedBy = "TMember")
    public List<TPartyAnswer> tPartyAnswerList;

}