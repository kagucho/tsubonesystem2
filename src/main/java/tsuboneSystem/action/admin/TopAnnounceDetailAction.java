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
