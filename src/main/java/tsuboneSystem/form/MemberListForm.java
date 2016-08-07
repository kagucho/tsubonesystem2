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

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Msg;


@Component(instance = InstanceType.SESSION) 
public class MemberListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* 名前　*/
	public String name;
	
	/* ハンドルネーム　*/
	public String hname;
	
	/* 入学年度　*/
	@DateType(datePatternStrict="yyyy",msg=@Msg(key="errors.date", resource=true))
	public String entrance;
	
	/* OBフラグ　*/
	public String obFlag;
	
	/* 仮登録フラグ　*/
	public String tempMemberFlag;
	
	//現在のページ番号
	public int page;
	
	//前のページがmemberList.jspならmemberListが入る
	public String referePage;
	
	/* メール不達フラグ */
    public String sendErrorFlag;
	
	/**
	 * 検索条件をクリアする
	 */
	public void clearSearchConditon() {
		name = null;
		hname = null;
		entrance = null;
		obFlag = null;
		tempMemberFlag = null;
	}
}
