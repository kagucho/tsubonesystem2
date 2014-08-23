package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.ImageUploadForm;

public class FileUploadAction {

	/** アクションネーム */
	public String actionName = "FileUpload";
	
	/** ImageUploadActionのアクションフォーム */
	@ActionForm
	@Resource
	protected ImageUploadForm imageUploadForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	
    @Execute(validator = false)
	public String index() {    	
        return "index.jsp";
	}
    
    @Execute(validator = false)
   	public String complete() {  
    	if (imageUploadForm.name == null){
    		
    	}
           return "index.jsp";
   	}

}
