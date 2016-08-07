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

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberListForm;
import tsuboneSystem.service.TMemberService;

public class MemberListAction {

	public String actionName = "MemberList";

	/** MemberListのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberListForm memberListForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** Memberのリスト */
	public List<TMember> memberItems;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** ログイン中のメンバー **/
	public TMember loginMember;

	/** 一覧表示件数 */
	int PAGE_LIMIT = 15;
	
	/** 最大ページ数 */
	public int maxPageCount;
	
	// 次のページがあればTRUE
	public boolean hasNext = false;

	// 前のページがあればTRUE
	public boolean hasPrev = false;

	public long total;

	@Resource
	protected LoginAdminDto loginAdminDto;

	@Execute(validator = false)
	public String index() {
		// 現在のページ番号
		int page = IntegerConversionUtil.toPrimitiveInt(Integer.valueOf(this.memberListForm.page));
		
		// 別なページから来た場合は常にページ番号は0
		if (!checkReferePage()) {
			memberListForm.page = 0;
			page = 0;
		}

		// ログイン中のユーザ情報を格納する
		setLoginUer();
		
		//検索条件の値をクリアする(このメソッドでは常に全件表示するため)
		memberListForm.clearSearchConditon();

		// 全てのメンバーを取得する
		memberItems = tMemberService.findByAllOrderEntrance(PAGE_LIMIT, page * PAGE_LIMIT);

		setPagger(tMemberService.findByAllOrderEntrance(-1, -1).size(), page);

		return "memberList.jsp";
	}

	/**
	 * 前のページがmemberListならTRUE。このメソッドはフレームワークの作りに反しているため極力いじらないでください。
	 * 
	 * @return
	 */
	private boolean checkReferePage() {
		String nowPage = RequestUtil.getRequest().getParameter("page");
		return nowPage != null && StringUtils.isNumeric(nowPage);
	}

	/**
	 * ログイン中のユーザを格納するカットポイント
	 */
	protected void setLoginUer() {
		loginMember = loginAdminDto.tMemberLogin;
	}

	protected void setPagger(int total, int nowPage) {
		this.total = total;
		// 前のページがあるかどうかを判定
		if (nowPage != 0) {
			hasPrev = true;
		}
		// 次のページが有るかどうかを判定
		if ((nowPage + 1) * PAGE_LIMIT < total) {
			hasNext = true;
		}
		
		//最大ページ数を設定
		maxPageCount = total / PAGE_LIMIT;
		
	}

	// 検索
	@Execute(validator = false, reset = "clearSearchConditon")
	public String onSearch() {
		// 登録されているメンバーの検索条件に一致するメンバーを一覧表示する。
		memberItems = tMemberService.findBySearch(memberListForm, -1, -1);
		
		//ログイン中のユーザ情報を格納
		setLoginUer();
		
		// 少し強引だが、検索した時はページング機能を使わない
		PAGE_LIMIT = memberItems.size();
		total = memberItems.size();

		return "memberList.jsp";
	}
	
	// 検索(仮登録メンバー用)
		@Execute(validator = false)
		public String onSearchTempMember() {
			
			//仮登録メンバーフラグをtrueにする。
			memberListForm.tempMemberFlag = "true";
			memberListForm.obFlag = "true";
			memberListForm.name = "";
			memberListForm.hname = "";
			memberListForm.entrance = "";
			
			// 登録されているメンバーの検索条件に一致するメンバーを一覧表示する。
			memberItems = tMemberService.findBySearch(memberListForm, -1, -1);
			
			//ログイン中のユーザ情報を格納
			setLoginUer();
			
			// 少し強引だが、検索した時はページング機能を使わない
			PAGE_LIMIT = memberItems.size();

			total = memberItems.size();

			return "memberList.jsp";
		}
		
		// 検索(仮登録メンバー用)
		@Execute(validator = false)
		public String onSearchNonDeliveryMail() {
			
			//仮登録メンバーフラグをtrueにする。
			memberListForm.sendErrorFlag = "true";
			memberListForm.tempMemberFlag = null;
			memberListForm.obFlag = "true";
			memberListForm.name = "";
			memberListForm.hname = "";
			memberListForm.entrance = "";
			
			// 登録されているメンバーの検索条件に一致するメンバーを一覧表示する。
			memberItems = tMemberService.findBySearch(memberListForm, -1, -1);
			
			//ログイン中のユーザ情報を格納
			setLoginUer();
			
			// 少し強引だが、検索した時はページング機能を使わない
			PAGE_LIMIT = memberItems.size();

			total = memberItems.size();

			return "memberList.jsp";
		}

}
