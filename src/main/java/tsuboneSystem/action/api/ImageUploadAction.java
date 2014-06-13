package tsuboneSystem.action.api;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.form.ImageUploadForm;

public class ImageUploadAction {
	
	
	/** ImageUploadActionのアクションフォーム */
	@ActionForm
	@Resource
	protected ImageUploadForm imageUploadForm;

	
    @Execute(validator = false)
	public String index() {
    	
    	if (imageUploadForm.file == null) {
    		
    	}
    	
        return null;
	}

}
