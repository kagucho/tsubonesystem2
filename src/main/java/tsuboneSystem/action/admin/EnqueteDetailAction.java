/*
x * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TEnquete;
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.form.EnqueteForm;
import tsuboneSystem.service.TEnqueteAnswerService;
import tsuboneSystem.service.TEnqueteService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class EnqueteDetailAction {

	/** アクションネーム */
	public String actionName = "ClubDetail";

	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected EnqueteForm enqueteForm;

	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** TEnqueteのサービスクラス */
	@Resource
	protected TEnqueteService tEnqueteService;

	/** TEnqueteAnswerServiceのサービスクラス */
	@Resource
	protected TEnqueteAnswerService tEnqueteAnswerService;

	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;

	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;

	/** ログインユーザ*/
	@Resource
	protected LoginAdminDto loginAdminDto;

	public final int num = 1;

	@Execute(validator = false, urlPattern = "{id}")
	public String index() {

		//アンケートを取得する
		TEnquete tEnquete = tEnqueteService.findByIdJoinTable(enqueteForm.id);
		//アンケートに答えた人の総数を計算
		int sum = 0;
		for (TEnqueteSelect tEnqueteSelect : tEnquete.tEnqueteSelect) {
			tEnqueteSelect.resultNum = tEnqueteAnswerService.resultNumCount(tEnqueteSelect.id);
			sum += Integer.parseInt(tEnqueteAnswerService.resultNumCount(tEnqueteSelect.id));
		}
		//アンケートに答えた人の総数をStringに変換
		tEnquete.totalCountNum = Integer.toString(sum);

		Beans.copy(tEnquete, enqueteForm).execute();
		enqueteForm.selectedContents = new String [tEnquete.tEnqueteSelect.size()];
		int i = 0;
		for (TEnqueteSelect tEnqueteSelect : tEnquete.tEnqueteSelect) {
			enqueteForm.selectedContents[i] = tEnqueteSelect.selectedContents;
			i++;
		}
		enqueteForm.userName = tEnquete.tMember.userName;

		return "enqueteDetail.jsp";
	}
}