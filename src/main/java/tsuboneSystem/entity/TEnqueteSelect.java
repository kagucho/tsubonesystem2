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
import javax.persistence.Transient;

/**
 * TEnqueteSelectエンティティクラス
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
	public Integer enqueteId;

	/* 選択肢 */
	@Column(nullable = false, unique = false)
	public String selectedContents;

	/* TEnqueteSelectをTEnqueteに関連づける */
	@ManyToOne
	@JoinColumn(name = "ENQUETE_ID", referencedColumnName = "ID")
	public TEnquete tEnquete;

	/** TEnqueteSelectをList<TEnqueteAnswer>に結びつける */
	@OneToMany(mappedBy = "tEnqueteSelect")
	public List<TEnqueteAnswer> tEnqueteAnswerList;

	/** 集計の時に、いくつ選択されたか格納する変数 */
	@Transient
	public String resultNum;
	
	/** 回答数に対する回答割合 */
	@Transient
	public String resultRate;
	
	/** 回答者対象総数に対する回答割合 */
	@Transient
	public String allRate;
	
	/** 詳細画面 円グラフの配色 */
	@Transient
	public int r;
	@Transient
	public int g;
	@Transient
	public int b;

}