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

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class LoginMemberDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** ログインID **/
	public  Integer memberId;
	
	/** ログインされている権限(Code) **/
	public Integer actorKindCode;
	
	/** ログインされている権限 **/
	public String actorKind;
	
	/** ログインしている人の情報 **/
	public TMember tMemberLogin;
	
	/** リダイレクト先ＵＲＬ */
	public String redirectURL;
	
}
