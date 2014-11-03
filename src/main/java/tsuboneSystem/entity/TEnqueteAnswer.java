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
 * @author ryuya
 */
@Entity
@Table(name = "T_ENQUETE_ANSWER")
public class TEnqueteAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Integer id;

    /* メンバーID */
    @Column()
    public Integer  memberId;

    /* 選ばれた選択肢 */
    @Column()
    public Integer  enqueteSelectedId;

    /* TEnqueteSelectをTEnqueteに関連づける */
    @ManyToOne
    @JoinColumn(name = "ENQUETE_SELECTED_ID", referencedColumnName = "ID")
    public TEnqueteSelect tEnqueteSelect;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    public TMember tMember;

}