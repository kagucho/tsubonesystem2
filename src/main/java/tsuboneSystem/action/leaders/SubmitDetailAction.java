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

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.service.TLeadersService;


/**
 * 
 * 作品 詳細
 * @author Hiroaki
 * 
 * */
public class SubmitDetailAction extends tsuboneSystem.action.admin.SubmitDetailAction{
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TLeadersService */
	@Resource
	public TLeadersService tLeadersService;
	
	
	/** 編集権限
	 *TODO 部長でかつ、自分の配下のメンバーのみ編集権限を与える処理を作る
	 *  */
	@Override
	public boolean getIsEdit(TSubmit tSubmit) {
		return true;
	}
}
