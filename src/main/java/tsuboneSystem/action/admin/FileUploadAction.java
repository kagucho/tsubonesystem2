package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.form.ImageUploadForm;

public class FileUploadAction {

	/** アクションネーム */
	public String actionName = "FileUpload";
	
	/** ImageUploadActionのアクションフォーム */
	@ActionForm
	@Resource
	protected ImageUploadForm imageUploadForm;

	
    @Execute(validator = false)
	public String index() {
    	
    	
    	
        return "index.jsp";
	}

}
