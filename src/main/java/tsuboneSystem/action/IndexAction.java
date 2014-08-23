package tsuboneSystem.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.form.TopForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TImageUploadService;

public class IndexAction {
	
	/** TopFormのアクションフォーム */
	@ActionForm
	@Resource
	protected TopForm topForm;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
    @Execute(validator = false)
	public String index() {
    	
    	//部の紹介のために一覧を取得する
    	topForm.clubList = tClubService.findAllOrderById();
    	
    	//背景画像名の一覧を取得する
    	topForm.imageList = tImageUploadService.findAll();
    	
        return "index.jsp";
	}
}
