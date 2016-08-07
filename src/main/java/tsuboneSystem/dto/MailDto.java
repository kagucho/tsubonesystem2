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

package tsuboneSystem.dto;

import java.util.List;

import tsuboneSystem.entity.TMember;

public class MailDto {
	
	/* メールを送信したメンバーのID　*/
	public Integer registMemberId;
	
	/* メールのタイトル　*/
	public String title;
	
	/* メールの内容　*/
	public String content;
	
	/** メールを送りたいメンバー　*/
	public List<TMember> tMemberSendList;
	
	/** メンバー一覧　*/
	public List<TMember> tMemberItem;
	
}
