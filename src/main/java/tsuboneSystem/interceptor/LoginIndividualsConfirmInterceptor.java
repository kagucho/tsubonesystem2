package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;

public class LoginIndividualsConfirmInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// ここの条件がtrueであればログイン済みと判断
		// ここの条件がfalseであればログインページへ移動
		return (!isExecuteMethod(invocation) || isLoggedIn()) ? invocation
				.proceed() : "/login/?redirect=true";
	}
	
	/**
	 * 実行されたActionに@Executeがついていたかどうか。
	 * @param invocation
	 * @return アノテーションがついていればtrue
	 */
	private boolean isExecuteMethod(MethodInvocation invocation) {
		return invocation.getMethod().isAnnotationPresent(Execute.class);
	}
	
	/**
	 * セッション上にDtoがあるか、あった場合その中にuserNameは保持されているか。
	 * @return 上記の条件を両方満たしていればtrue
	 */
	private boolean isLoggedIn() {
		return (loginIndividualsDto != null && loginIndividualsDto.memberId != null);
	}
}
