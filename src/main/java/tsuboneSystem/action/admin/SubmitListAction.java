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

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.SubmitListForm;
import tsuboneSystem.service.TImageUploadService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TSubmitService;
import tsuboneSystem.service.TSubmitTagKindService;
import tsuboneSystem.service.TTopAnnounceService;


/**
 * 
 * TOPお知らせ 一覧
 * @author Hiroaki
 * 
 * */
public class SubmitListAction {
	
	/** アクションネーム */
	public String actionName = "SubmitList";
	

	/** アクションフォーム */
	@Resource
	@ActionForm
	public SubmitListForm submitListForm;
	
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
	@Execute(validator = false)
	public String index() {
		// 作品分類タグの選択肢
		submitListForm.submitTagNameMap = tSubmitTagKindService.getSubmitTagKindMapIS();
		
		// 紐付けるおしらせ
		submitListForm.topAnnounceMap = tTopAnnounceService.getTopAnnounceMapIS();
		
		// 一覧の取得
		submitListForm.list = tSubmitService.findAllOrderById();

		return "submitList.jsp";
	}
}
