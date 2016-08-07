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

import java.util.HashMap;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SexCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberDetailAction {
	
	public String actionName = "MemberDetail";
	
	/** Memberフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;

	
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {

        memberForm.clubMap = tClubService.getClubMapIS();
        
        /** Idから対象のメンバー情報を検索する　**/
        TMember memberDetail = new TMember();
        memberDetail = tMemberService.findById(memberForm.id);
        //検索した結果をformにコピー
        Beans.copy(memberDetail, memberForm).execute();	

        memberForm.sexMap = new HashMap<String, String>();
        for (Integer i=1; i<=3; i++) {
        	memberForm.sexMap.put(i.toString(), SexCode.getnameByCode(i.toString()));
        }
        
        
        /** Idから対象のメンバーが所属している部の一覧を検索する　**/
        memberForm.tMemberClubList = tMemberClubService.findByMemberId(memberForm.id.toString());
        
        //パスワードは表示しない
        memberForm.password = "(パスワードは初期化のみ可能です)";
        	
        return "memberDetail.jsp";
	}
}
