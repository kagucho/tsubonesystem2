/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tsuboneSystem.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.form.AnnounceListForm;
import tsuboneSystem.original.util.TsuboneSystemUtil;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TSubmitService;
import tsuboneSystem.service.TSubmitTagKindService;
import tsuboneSystem.service.TTopAnnounceService;

public class AnnounceListAction {
	
	public String actionName = "作品一覧";
	
	/** AnnounceListActionのアクションフォーム */
	@ActionForm
	@Resource
	protected AnnounceListForm announceListForm;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TTopAnnounceServiceのサービスクラス */
	@Resource
	protected TTopAnnounceService tTopAnnounceService;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
	/** TSubmitService */
	@Resource
	public TSubmitService tSubmitService;
	
	/** TMemberService */
	@Resource
	public TMemberService tMemberService;
	
	/** TSubmitTagKindService */
	@Resource
	public TSubmitTagKindService tSubmitTagKindService;
	
	
	/**
	 * おしらせ(作品展示)画面表示
	 * 
	 * */
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	// 作品分類タグの選択肢
    	announceListForm.submitTagNameMap = tSubmitTagKindService.getSubmitTagKindMap();
    	// トップアナウンス情報
    	announceListForm.tTopAnnounce = tTopAnnounceService.findById(announceListForm.id);
    	// 作品情報
    	announceListForm.tSubmitList = announceListForm.tTopAnnounce.tSubmitList;
    	List<TSubmit> subList = new ArrayList<TSubmit>();
    	for (TSubmit tSubmit : announceListForm.tSubmitList) {
    		TSubmit sub = tSubmit;
    		if (tSubmit.submitCaptionImageId != null) {
    			sub.tImageUpload = tImageUploadService.findById(tSubmit.submitCaptionImageId);
    		}
    		subList.add(sub);
    	}
    	
        return "announceList.jsp";
    }
    
    /**
	 * ダウンロード処理
	 * 
	 * */
    @Execute(validator = false, urlPattern = "download/{tSubmitId}")
	public String download() {
       return TsuboneSystemUtil.submitDownload(announceListForm.tSubmitId);
    }
    
}
