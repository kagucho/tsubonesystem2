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

package tsuboneSystem.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class MailListForm implements Serializable{

	private static final long serialVersionUID = 1L;

	/* id　*/
	public Integer id;

	/* メールを送信したメンバーのID　*/
	public Integer registMemberId;

	/* メールのタイトル　*/
	public String title;

	/* メールの内容　*/
	public String content;

	/* 自分に届いたメールチェック */
	public String myMailCheck;

	/** Member一覧　*/
	public List<TMember> tMemberItem;

	/** Mail一覧　*/
	public List<TMail> tMailItem;

	/** MailSendMember一覧　*/
	public List<TMailSendMember> tMailSendMemberItem;

	/** 全メンバーのリスト　*/
	public List<TMember> tMemberAllList;

	/** memberのマップ */
	public Map<Integer,String> memberMapIS;


}
