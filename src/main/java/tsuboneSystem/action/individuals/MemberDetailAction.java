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
package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;

public class MemberDetailAction extends tsuboneSystem.action.admin.MemberDetailAction{
	
	public String actionName = "MemberDetail";
	
	//ログイン者の情報
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	//現在見ている情報が自分のものならTRUE
	public boolean isMyInfo = true;
	
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {
		//まずは詳細を表示するメンバーの情報を格納
		super.detail();
		
		//もし表示する情報が自分でなければ一部の情報を隠す
		if (!memberForm.id.equals(loginIndividualsDto.memberId)) {
			memberForm.password = "*****";
			memberForm.tel1 = "***";
			memberForm.tel2 = "****";
			memberForm.tel3 = "****";
			memberForm.userName = "*****";
			//フラグを書き換える
			isMyInfo = false;
		}
		
		return "memberDetail.jsp";
	}
}
