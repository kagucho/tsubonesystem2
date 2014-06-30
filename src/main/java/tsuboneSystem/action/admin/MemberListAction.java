/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
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
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberListForm;
import tsuboneSystem.service.TMemberService;

public class MemberListAction {

	public String actionName = "MemberList";

	/** MemberListのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberListForm memberListForm;

	/** Memberのリスト */
	public List<TMember> memberItems;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** ログイン中のメンバー **/
	public TMember loginMember;

	/** 一覧表示件数 */
	int PAGE_LIMIT = 15;

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
	}

	// 検索
	@Execute(validator = false)
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

}
