package tsuboneSystem.action.admin;

import javax.annotation.Resource;

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
 * 作品 詳細
 * @author Hiroaki
 * 
 * */
public class SubmitDetailAction {
	
	/** アクションネーム */
	public String actionName = "SubmitDetail";
	
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
	
	/** 一覧画面 */
	@Execute(validator = false, urlPattern = "{id}")
	public String index() {
		// 表示対象特定
		TSubmit tSubmit = new TSubmit();
		tSubmit = tSubmitService.findById(submitForm.id);
		if (!SubmitProductFileTypeCode.DTM.getCode().equals(tSubmit.submitProductFileType.toString())) {
			tSubmit.tImageUpload = tImageUploadService.findById(tSubmit.submitCaptionImageId);
		}
		Beans.copy(tSubmit, submitForm).execute();
		submitForm.tImageUpload = tSubmit.tImageUpload;
		
		// 作品分類タグの選択肢
		submitForm.submitTagNameMap = tSubmitTagKindService.getSubmitTagKindMap();
		
		// 作品の提出者(管理者などは他人の作品を提出できる)
		submitForm.submitMemberMap = tMemberService.getMemberMapSS();
		
		// 紐付けるおしらせ
		submitForm.topAnnounceMap = tTopAnnounceService.getTopAnnounceMap();
		
		// 作品種大別
		submitForm.submitProductFileCodeMap = SubmitProductFileTypeCode.getSubmitProductFileCodeMap();
		
		submitForm.registFlag = false;
		return "submitDetail.jsp";
	}
	
	/** ダウンロード処理 */
	@Execute(validator = false)
	public String download() {
		return TsuboneSystemUtil.submitDownload(submitForm.id);
	}
}
