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
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SubmitProductFileTypeCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.form.SubmitForm;
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
public class SubmitDeleteAction {
	
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
	@Execute(validator = false, reset = "resetInput", urlPattern = "{id}")
	public String index() {
		/** 2重登録防止のためのTokenの生成　**/
		TokenProcessor.getInstance().saveToken(request);
		
		// 表示対象特定
		TSubmit tSubmit = new TSubmit();
		tSubmit = tSubmitService.findById(submitForm.id);
		if (!SubmitProductFileTypeCode.DTM.getCode().equals(tSubmit.submitProductFileType.toString())) {
			tSubmit.tImageUpload = tImageUploadService.findById(tSubmit.submitCaptionImageId);
		}
		Beans.copy(tSubmit, submitForm).execute();
		submitForm.tImageUpload = tSubmit.tImageUpload;
		
		if (!getIsEdit(tSubmit)) {
			return "/submitList/";
		}
		
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
		return "submitConfirm.jsp";
	}
	
	/** 完了画面 */
	@Execute(validator = false)
	public String complete() {
		/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う **/
		if (TokenProcessor.getInstance().isTokenValid(request, true)) {
			
			// DB登録
			TSubmit tSubmit = tSubmitService.findById(submitForm.id);
			tSubmit.deleteFlag = true;
			tSubmitService.update(tSubmit);
		}
		return "submitComplete.jsp";
	}
	
	/** 編集権限
	 * 
	 *  */
	public boolean getIsEdit(TSubmit tSubmit) {
		return true;
	}
}
