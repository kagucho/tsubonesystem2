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
 * TPartyQuestionのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_PARTY_QUESTION")
public class TPartyQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    /** PartyのID */
    @Column()
    public Integer  partyId;
    
    /** MemberのID */
    @Column()
    public Integer  memberId;
    
    /** 質問内容 */
    @Column(columnDefinition ="mediumtext")
    public String question;
    
    /** 質問を会議の登録者にメールで送ったか */
    @Column()
    public boolean questionSend;
    
    /** IDをTParty(ID)に関連付ける☆ */
    @ManyToOne
    @JoinColumn(name = "PARTY_ID", referencedColumnName = "ID")
    public TParty tParty;
    
    /** メンバーのIDをTMember(ID)に関連付ける★ */
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;
    
    /** questionIdをTPartyQuestionに結びつける */
    @OneToMany(mappedBy = "TPartyQuestion")
    public List<TPartyAnswer> tPartyAnswerList;

}