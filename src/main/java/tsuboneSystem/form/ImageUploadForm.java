package tsuboneSystem.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION) 
public class ImageUploadForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 画像ファイル **/
	public FormFile file;
	
	/** 画像ファイル **/
	public String Formfile;

	
}
