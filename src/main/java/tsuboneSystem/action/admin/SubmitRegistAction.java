
package tsuboneSystem.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SubmitProductFileTypeCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.form.SubmitForm;
import tsuboneSystem.original.util.TsuboneSystemUtil;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TSubmitService;
import tsuboneSystem.service.TSubmitTagKindService;
import tsuboneSystem.service.TTopAnnounceService;


/**
 * 
 * 作品 登録
 * @author Hiroaki
 * 
 * */
public class SubmitRegistAction {
	
	/** アクションネーム */
	public String actionName = "SubmitRegist";
	
	/** アクションフォーム */
	@Resource
	@ActionForm
	public SubmitForm submitForm;
	
	/** TSubmitService */
	@Resource
	public TSubmitService tSubmitService;
	
	/** TSubmitTagKindService */
	@Resource
	public TSubmitTagKindService tSubmitTagKindService;
	
	/** TImageUploadService */
	@Resource
	public TImageUploadService tImageUploadService;
	
	/** TMemberService */
	@Resource
	public TMemberService tMemberService;
	
	/** TTopAnnounceService */
	@Resource
	public TTopAnnounceService tTopAnnounceService;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 入力画面 */
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		/** 2重登録防止のためのTokenの生成　**/
		TokenProcessor.getInstance().saveToken(request);
		
		// 作品分類タグの選択肢
		submitForm.submitTagNameMap = tSubmitTagKindService.getSubmitTagKindMap();
		
		// 作品の提出者(管理者などは他人の作品を提出できる)
		submitForm.submitMemberMap = tMemberService.getMemberMapSS();
		
		// 紐付けるおしらせ
		submitForm.topAnnounceMap = tTopAnnounceService.getTopAnnounceMap();
		
		// 作品種大別
		submitForm.submitProductFileCodeMap = SubmitProductFileTypeCode.getSubmitProductFileCodeMap();
		
		return viewinput();
	}
	
	/** 入力画面(表示) */
	@Execute(validator = false)
	public String viewinput() {
		return "submitInput.jsp";
	}
	
	/** 確認画面 */
	@Execute(validator = true, validate="validateBase", input="viewinput", stopOnValidationError = false)
	public String confirm() {
		// ファイル名を取得
		if (submitForm.submitFile.getFileSize() > 0) {
			submitForm.submitProductFileName = submitForm.submitFile.getFileName();
		}
		submitForm.registFlag = true;
		return "submitConfirm.jsp";
	}
	
	/** 完了画面 */
	@Execute(validator = false)
	public String complete() {
		/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う **/
		if (TokenProcessor.getInstance().isTokenValid(request, true)) {
			
			// 提出物の登録
			TsuboneSystemUtil.createSubmitFile(submitForm);
			// DB登録
			TSubmit tSubmit = new TSubmit();
			Beans.copy(submitForm, tSubmit).execute();
			tSubmitService.insertCustom(tSubmit);
			// 詳細画面リンク用のID
			submitForm.id = tSubmit.id;
		}
		return "submitComplete.jsp";
	}
}
