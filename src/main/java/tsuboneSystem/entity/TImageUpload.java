package tsuboneSystem.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TImageUploadのエンティティクラス
 * 
 * @author oowada
 */
@Entity
@Table(name = "T_IMAGE_UPLOAD")
public class TImageUpload implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true, unique = true)
    public Integer id;
    
    //ファイル名
    @Column()
    public String fileName;
    
    //ファイルパス
    @Column()
    public String filePath;
    
    // 画像の使用目的
    @Column()
    public Integer ImageFilePurpose;
    
    @OneToOne(mappedBy = "tImageUpload")
    public TTopAnnounce tTopAnnounce;
    
    @OneToOne(mappedBy = "tImageUpload")
    public TSubmit tSubmit;

}