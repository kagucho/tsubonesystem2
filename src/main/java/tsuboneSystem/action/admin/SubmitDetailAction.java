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
		
		// 編集権限
		submitForm.isEdit = getIsEdit(tSubmit);
		
		return "submitDetail.jsp";
	}
	
	/** ダウンロード処理 */
	@Execute(validator = false)
	public String download() {
		return TsuboneSystemUtil.submitDownload(submitForm.id);
	}
	
	/** 編集権限
	 * 
	 *  */
	public boolean getIsEdit(TSubmit tSubmit) {
		return true;
	}
}
