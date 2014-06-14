package tsuboneSystem.form;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.Required;

public class MemberUploadForm {
	
	@Required
	public FormFile form;
}
