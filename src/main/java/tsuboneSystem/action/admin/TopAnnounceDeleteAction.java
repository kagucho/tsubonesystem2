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

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TTopAnnounce;
import tsuboneSystem.form.TopAnnounceForm;
import tsuboneSystem.original.util.TsuboneSystemUtil;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TTopAnnounceService;


/**
 * 
 * TOPお知らせ 更新
 * @author Hiroaki
 * 
 * */
public class TopAnnounceDeleteAction {
	
	/** アクションネーム */
	public String actionName = "AnnounceUpdate";
	
	/** アクションフォーム */
	@Resource
	@ActionForm
	public TopAnnounceForm topAnnounceForm;
	
	/** TTopAnnounceService */
	@Resource
	public TTopAnnounceService tTopAnnounceService;
	
	/** TImageUploadService */
	@Resource
	public TImageUploadService tImageUploadService;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 確認画面 */
	@Execute(validator = false, urlPattern = "{id}")
	public String confirm() {
		/** 2重登録防止のためのTokenの生成　**/
		TokenProcessor.getInstance().saveToken(request);
		// 更新対象の特定
		TTopAnnounce topAnnounce = tTopAnnounceService.findById(topAnnounceForm.id);
		Beans.copy(topAnnounce, topAnnounceForm).execute();
		return "topAnnounceConfirm.jsp";
	}
	
	/** 完了画面 */
	@Execute(validator = false)
	public String complete() {
		/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う **/
		if (TokenProcessor.getInstance().isTokenValid(request, true)) {
			// エンティティにコピー
			TTopAnnounce tTopAnnounce = tTopAnnounceService.findById(topAnnounceForm.id);
			tTopAnnounce.deleteFlag = true;
			// 画像ファイルがある場合は削除
			if (tTopAnnounce.imageId != null) {
				TImageUpload tImageUpload = tImageUploadService.findById(tTopAnnounce.imageId);
				if (!TsuboneSystemUtil.deleteFile(tImageUpload)) {
					tTopAnnounce.imageId = null;
				}
			}
			// 登録
			tTopAnnounceService.update(tTopAnnounce);
		}
		return "topAnnounceComplete.jsp";
	}
}
