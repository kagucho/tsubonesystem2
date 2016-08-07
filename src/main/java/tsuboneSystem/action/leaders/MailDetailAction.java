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

package tsuboneSystem.action.leaders;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.form.MailForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailService;

public class MailDetailAction {
	
	/** アクションネーム */
	public String actionName = "MailDetail";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected MailForm mailForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 入力画面(送信先選択) */
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
        TMail mail  = tMailService.findById(mailForm.id);
        
        //チェックが通らなければ共通エラーページ
        if (!check(mail)) {
        	return "/common/error.jsp";
        }
        Beans.copy(mail, mailForm).execute();
        return "mailDetail.jsp";
	}

    /**
     * 各種チェックを行う
     * @param mail
     * @return 正しいならTRUE
     */
	protected boolean check(TMail mail) {
		return mail != null;
	}

}
