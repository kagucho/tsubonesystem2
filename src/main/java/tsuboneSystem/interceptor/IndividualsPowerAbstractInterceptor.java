package tsuboneSystem.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

public abstract class IndividualsPowerAbstractInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	
	@Resource
	HttpServletRequest request;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (isExecuteMethod(invocation)) {
			if (!isHavePower()) {
				return "/login/?redirect=true";
			}
		}
		Object str =  invocation.proceed();
		return str;
	}

	//権限があればTRUE
	abstract protected boolean isHavePower();

	/**
	 * 実行されたActionに@Executeがついていたかどうか。
	 * @param invocation
	 * @return アノテーションがついていればtrue
	 */
	protected boolean isExecuteMethod(MethodInvocation invocation) {
		return invocation.getMethod().isAnnotationPresent(Execute.class);
	}
}
