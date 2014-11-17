package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TTopAnnounce;
import tsuboneSystem.form.TopAnnounceForm;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TTopAnnounceService;


/**
 * 
 * TOPお知らせ 詳細
 * @author Hiroaki
 * 
 * */
public class TopAnnounceDetailAction {
	
	/** アクションネーム */
	public String actionName = "AnnounceDetail";
	
	/** アクションフォーム */
	@Resource
	@ActionForm
	public TopAnnounceForm topAnnounceForm;
	
	/** TTopAnnounceService */
	@Resource
	public TTopAnnounceService tTopAnnounceService;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** 一覧画面 */
	@Execute(validator = false, urlPattern = "{id}")
	public String index() {
		// 表示対象特定
		TTopAnnounce tTopAnnounce = tTopAnnounceService.findById(topAnnounceForm.id);
		if (tTopAnnounce.imageId != null) {
			tTopAnnounce.tImageUpload = tImageUploadService.findById(tTopAnnounce.imageId);
		}
		Beans.copy(tTopAnnounce, topAnnounceForm).execute();
		return "topAnnounceDetail.jsp";
	}
}
