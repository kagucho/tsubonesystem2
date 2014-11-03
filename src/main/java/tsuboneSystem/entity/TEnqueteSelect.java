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
 * @author ryuya
 */
@Entity
@Table(name = "T_ENQUETE_SELECT")
public class TEnqueteSelect implements Serializable {

	private static final long serialVersionUID = 1L;

    /* idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Integer id;

    /* アンケートID */
    @Column(nullable = true, unique = false)
    public Integer  enqueteId;

    /* 選択肢1 */
    @Column(nullable = false, unique = false)
    public String  selectedContents;

    /* TEnqueteSelectをTEnqueteに関連づける */
    @ManyToOne
    @JoinColumn(name="ENQUETE_ID",referencedColumnName="ID")
    public TEnquete tEnquete;

    /** TEnqueteSelectをList<TEnqueteAnswer>に結びつける */
    @OneToMany(mappedBy = "tEnqueteSelect")
    public List<TEnqueteAnswer> tEnqueteAnswerList;
}