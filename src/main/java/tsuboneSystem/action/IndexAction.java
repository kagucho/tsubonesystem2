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

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.ImageFilePurposeCode;
import tsuboneSystem.entity.TContact;
import tsuboneSystem.entity.TTopAnnounce;
import tsuboneSystem.form.TopForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TContactService;
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
	
	/** TTopAnnounceService */
	@Resource
	public TContactService tContactService;
	
	
	/**
	 * TOP画面表示
	 * 
	 * */
    @Execute(validator = false)
	public String index() {
    	
    	//部の紹介のために一覧を取得する
    	topForm.clubList = tClubService.findAllOrderById();
    	
    	//背景画像名の一覧を取得する
    	topForm.imageList = tImageUploadService.findByImageFilePurposeCode(ImageFilePurposeCode.TOP_BACK.getCode());
    	
    	// お知らせ一覧
    	topForm.topAnnounceList = tTopAnnounceService.checkDateList();
    	List<TTopAnnounce> list = new ArrayList<TTopAnnounce>();
    	for (TTopAnnounce tTopAnnounce : topForm.topAnnounceList) {
    		if (tTopAnnounce.imageId != null) {
    			tTopAnnounce.tImageUpload = tImageUploadService.findById(tTopAnnounce.imageId);
    		}
    		if (tTopAnnounce.tSubmitList.size() >0) {
    			tTopAnnounce.submitFlag = true;
    		}
    		list.add(tTopAnnounce);
		}
    	topForm.topAnnounceList = list;
    	
        return "index.jsp";
	}
    
    /**
     * 問い合わせ完了画面
     * 
     * 
     * */
    @Execute(validator = true, input = "index.jsp")
    public String contact() {
    	
    	// 登録
    	TContact tContact = new TContact();
    	Beans.copy(topForm, tContact).execute();
    	tContactService.insert(tContact);
    	
    	return "contactComplete.jsp";
    }
}
