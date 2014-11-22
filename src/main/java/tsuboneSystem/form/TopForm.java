package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;


import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TTopAnnounce;

@Component(instance = InstanceType.SESSION) 
public class TopForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//質問者
	@Required(target = "contact")
	public String name;
	
	//質問者のメールアドレス
	@Required(target = "contact")
	@EmailType(target = "contact")
	public String mail;
	
	//質問(タイトル)
	@Required(target = "contact")
	public String subject;
	
	//質問(内容)
	@Required(target = "contact")
	public String message;
	
	//トップの部紹介一覧
	public List<TClub> clubList;
	
	//トップ背景画像一覧(画像のファイル名一覧)
	public List<TImageUpload> imageList;
	
	// おしらせ一覧
	public List<TTopAnnounce> topAnnounceList;
	
}
