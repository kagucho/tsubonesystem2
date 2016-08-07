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

import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TEnqueteAnswer;
import tsuboneSystem.form.EnqueteReplyForm;
import tsuboneSystem.service.TEnqueteAnswerService;
import tsuboneSystem.service.TEnqueteSelectService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class EnqueteReplyAction {

	/** アクションネーム */
	public String actionName = "EnqueteReply";

	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected EnqueteReplyForm enqueteReplyForm;

	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** TEnqueteSelectのサービスクラス */
	@Resource
	protected TEnqueteSelectService tEnqueteSelectService;

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

	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String index() {
		// 過去に答えたアンケートの回答を取得する
		enqueteReplyForm.tEnqueteAnswerOld = tEnqueteAnswerService.findByIdAndUserId(enqueteReplyForm.id,
				loginMemberDto.memberId);

		if (enqueteReplyForm.tEnqueteAnswerOld != null) {
			enqueteReplyForm.answer = enqueteReplyForm.tEnqueteAnswerOld.enqueteSelectedId.toString();
			enqueteReplyForm.isOld = true;
		} else {
			enqueteReplyForm.isOld = false;
		}

		// 選択肢を取得する
		Map<String, String> enqueteSelectMap = tEnqueteSelectService.enqueteSelectMap(enqueteReplyForm.id);

		enqueteReplyForm.enqueteSelectMap = enqueteSelectMap;

		enqueteReplyForm.memberId = loginAdminDto.memberId;

		return "enqueteReply.jsp";
	}

	@Execute(validator = true, validate = "validateBase", input = "enqueteReply.jsp", stopOnValidationError = false)
	public String confirm() {
		return "enqueteReplyConfirm.jsp";
	}

	@Execute(validator = false)
	public String complete() {
		if (!enqueteReplyForm.isOld) {
			// DBに登録
			TEnqueteAnswer tEnqueteAnswer = new TEnqueteAnswer();
			tEnqueteAnswer.memberId = loginMemberDto.memberId;
			tEnqueteAnswer.enqueteSelectedId = Integer.valueOf(enqueteReplyForm.answer);
			tEnqueteAnswerService.insert(tEnqueteAnswer);
		} else {
			enqueteReplyForm.tEnqueteAnswerOld.enqueteSelectedId = Integer.valueOf(enqueteReplyForm.answer);
			tEnqueteAnswerService.update(enqueteReplyForm.tEnqueteAnswerOld);
		}

		return "enqueteReplyComplete.jsp";
	}

}
