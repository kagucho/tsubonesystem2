package tsuboneSystem.action.api;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.form.ImageUploadForm;
import tsuboneSystem.original.util.TsuboneSystemUtil;
import tsuboneSystem.service.TImageUploadService;

public class DisplyImageAction {
	
	HttpServletResponse response;
	
	/** ImageUploadActionのアクションフォーム */
	@ActionForm
	@Resource
	protected ImageUploadForm imageUploadForm;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
	@Execute(validator = false, urlPattern = "{id}")
	public String index() throws IOException {

		//出力するファイル
		TImageUpload tImageUpload = tImageUploadService.findById(imageUploadForm.id);
		
		// 出力
		if (tImageUpload != null) {
			TsuboneSystemUtil.downloadCommon(tImageUpload.filePath, tImageUpload.fileName);
			return null;
		}
		
		return null;
    }
}
