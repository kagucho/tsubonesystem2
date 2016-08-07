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

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.service.TLeadersService;

public class MemberDetailAction extends tsuboneSystem.action.admin.MemberDetailAction{
	
	public String actionName = "MemberDetail";
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	//ログイン者の情報
	@Resource
	public LoginLeadersDto loginLeadersDto;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	//現在見ている情報が自分のものならTRUE
	public boolean isMyInfo = true;
	
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {
		//まずは詳細を表示するメンバーの情報を格納
		super.detail();
		
		if (!memberForm.id.equals(loginLeadersDto.memberId)) {
			//秘匿情報の権限がない場合
			if(!loginLeadersDto.secretInformation){
				//もし表示する情報が自分でなければ一部の情報を隠す
				memberForm.password = "*****";
				memberForm.tel1 = "***";
				memberForm.tel2 = "****";
				memberForm.tel3 = "****";
				memberForm.userName = "*****";
			}
			//フラグを書き換える
			isMyInfo = false;
		}
		return "memberDetail.jsp";
	}
}
