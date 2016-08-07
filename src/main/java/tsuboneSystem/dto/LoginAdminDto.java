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

import java.io.Serializable;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class LoginAdminDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/** ログインID **/
	public  Integer memberId;
	
	/** ログインしている人の情報 **/
	public TMember tMemberLogin;
	
	/** 役職の情報 **/
	public List<TLeaders> tLeadersList;
	
	/** 役職の情報(複数の場合は一番上位の役職種) **/
	public Integer memberKind;
	
	/** 部長の場合の情報 **/
	public TClub tClub;
	
	/** リダイレクト先ＵＲＬ */
	public String redirectURL;
		
}
