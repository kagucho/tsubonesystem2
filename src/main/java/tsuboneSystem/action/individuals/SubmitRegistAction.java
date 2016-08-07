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

package tsuboneSystem.action.individuals;

/**
 * 
 * 作品 登録
 * @author Hiroaki
 * 
 * */
public class SubmitRegistAction extends tsuboneSystem.action.admin.SubmitRegistAction{
	
	// 一般メンバーは自分の作品のみ
	@Override
	public String index() {
		submitForm.registId = loginMemberDto.memberId.toString();
		return super.index();
	}
	
}
