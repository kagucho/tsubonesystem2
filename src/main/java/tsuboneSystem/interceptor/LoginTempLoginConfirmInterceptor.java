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

package tsuboneSystem.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginTempLoginDto;
import tsuboneSystem.service.TTempMessageService;

/**
 * 仮登録のためのインターセプター
 * 
 * TMemberと型が違うためextendsせず
 * 
 **/
public class LoginTempLoginConfirmInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	@Resource
	HttpServletRequest request;
	
	@Resource
	TTempMessageService tTempMessageService;
	
	@Resource
	protected LoginTempLoginDto loginTempLoginDto;
	
	protected boolean isLogined() {
		return (loginTempLoginDto != null && loginTempLoginDto.memberId != null);
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (isExecuteMethod(invocation)) {
			if (!isLogined()) {
				return "/login/?redirect=true";
			}
		}
		//一時メッセージの設定
		tempMessageProcess(invocation);
		Object str =  invocation.proceed();
		
		return str;
	}
	
	/**
	 * 一時メッセージをセットする
	 * @param invocation 
	 */
	protected void tempMessageProcess(MethodInvocation invocation) {
		request.setAttribute("tempMessage", tTempMessageService.findByMemberId(getLoginMemberId()));
	}
	
	/**
	 * 実行されたActionに@Executeがついていたかどうか。
	 * @param invocation
	 * @return アノテーションがついていればtrue
	 */
	protected boolean isExecuteMethod(MethodInvocation invocation) {
		return invocation.getMethod().isAnnotationPresent(Execute.class);
	}
	
	Integer getLoginMemberId() {
		if (loginTempLoginDto == null) {
			return null;
		}
		return loginTempLoginDto.memberId;
	}

}
