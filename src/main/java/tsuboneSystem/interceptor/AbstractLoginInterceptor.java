package tsuboneSystem.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.service.TTempMessageService;

public abstract class AbstractLoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	
	@Resource
	HttpServletRequest request;
	
	@Resource
	TTempMessageService tTempMessageService;
	
	String warUrl = new String();
	String[] urls;
	String url;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (isExecuteMethod(invocation)) {
			if (!isLogined()) {
				
				//遷移先を取得しておく
				warUrl = RequestUtil.getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
				
				//先頭の”/tsuboneSystem/”を取る
				urls = warUrl.split("/",3);
				StringBuffer bf = new StringBuffer();
//				bf.append("/");
				bf.append(warUrl);
				bf.append("?redirect=true");
				setRedirectUrl(new String(bf));
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

	//ログインしていたらTRUE
	abstract protected boolean isLogined();

	//ログイン中のTMmeberを取得(ログインしていないならNULL)
	abstract TMember getLoginTMember();
	
	//ログイン中のMemberIdを取得(ログインしていないならNULL)
	abstract Integer getLoginMemberId();
	
	/**
	 * 実行されたActionに@Executeがついていたかどうか。
	 * @param invocation
	 * @return アノテーションがついていればtrue
	 */
	protected boolean isExecuteMethod(MethodInvocation invocation) {
		return invocation.getMethod().isAnnotationPresent(Execute.class);
	}
	
	//遷移先があった場合はその遷移先をセットする。
	abstract protected void setRedirectUrl(String Url);
	
}
