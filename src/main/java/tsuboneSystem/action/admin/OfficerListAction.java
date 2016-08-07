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

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.OfficerForm;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;

public class OfficerListAction {
	
	public String actionName = "OfficerList";
	
	/** ClubFormのフォーム */
	@ActionForm
	@Resource
	protected OfficerForm officerForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;

	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	
    @Execute(validator = false)
    @RemoveSession(name="officerForm")
	public String index() {
    	
    	//局長
    	officerForm.tLeadersChief = tAdminService.findByKind(LeadersKindCode.CHIEF.getCode());
    	
    	//副局長
    	officerForm.tLeadersSubChief = tAdminService.findByKind(LeadersKindCode.SUB_CHIEF.getCode());
    	
    	//会計
    	officerForm.tLeadersAccounts = tLeadersService.findByKind(LeadersKindCode.ACCOUNT.getCode());
	
    	//各部長の一覧
    	officerForm.tClubLeaderList = tClubService.findAllInTmember();
    	
    	//合宿委員
    	officerForm.tLeadersGassyuku = tLeadersService.findByKind(LeadersKindCode.GASSYUKU.getCode());
    	
    	//理大祭実行委員
    	officerForm.tLeadersRidaisai = tLeadersService.findByKind(LeadersKindCode.RIDAISAI.getCode());
    	
    	//その他
    	officerForm.tLeadersEtc = tLeadersService.findByKind(LeadersKindCode.ETC.getCode());
    	
    	//web管理者
    	officerForm.tLeadersWebAdmin = tAdminService.findByKind(LeadersKindCode.WEBADMIN.getCode());
	
        return "OfficerList.jsp";
	}
}
