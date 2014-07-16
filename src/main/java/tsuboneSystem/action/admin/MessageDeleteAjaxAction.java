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
