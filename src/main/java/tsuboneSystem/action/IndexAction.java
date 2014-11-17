package tsuboneSystem.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TTopAnnounce;
import tsuboneSystem.form.TopForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TTopAnnounceService;

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
	
	/** TTopAnnounceService */
	@Resource
	public TTopAnnounceService tTopAnnounceService;
	
    @Execute(validator = false)
	public String index() {
    	
    	//部の紹介のために一覧を取得する
    	topForm.clubList = tClubService.findAllOrderById();
    	
    	//背景画像名の一覧を取得する
    	topForm.imageList = tImageUploadService.findAll();
    	
    	// お知らせ一覧
    	topForm.topAnnounceList = tTopAnnounceService.checkDateList();
    	List<TTopAnnounce> list = new ArrayList<TTopAnnounce>();
    	for (TTopAnnounce tTopAnnounce : topForm.topAnnounceList) {
    		if (tTopAnnounce.imageId != null) {
    			tTopAnnounce.tImageUpload = tImageUploadService.findById(tTopAnnounce.imageId);
    		}
    		list.add(tTopAnnounce);
		}
    	topForm.topAnnounceList = list;
    	
        return "index.jsp";
	}
}
