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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TTempMessage;
import tsuboneSystem.service.TTempMessageService;

public class MessageDeleteAjaxAction {
	@Resource
	HttpServletRequest request;
	
	@Resource
	TTempMessageService ttempMessageService;
	
	@Resource
	LoginAdminDto loginAdminDto;
	
	@Execute(validator=false)
	public String index() {
		//getパラメータのIDを取得
		String id = request.getParameter("messageId");
		if (id == null || !NumberUtils.isNumber(id)) {
			return null;
		}
		TTempMessage tTempMessage = ttempMessageService.findById(Integer.parseInt(id));
		if (tTempMessage == null) {
			return null;
		}
		//自分のメッセージでないなら何もしない
		if (tTempMessage.targetMemberId.equals(getLoginMemberId())) {
			
		}
		tTempMessage.deleteFlag = true;
		ttempMessageService.update(tTempMessage);
		return null;
	}

	protected Integer getLoginMemberId() {
		return loginAdminDto.memberId;
	}
}
