package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import org.seasar.struts.util.RequestUtil;

import tsuboneSystem.dto.LoginLeadersDto;

public class LeadersMemberUpdatePowerInterceptor extends LeadersPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	String warUrl = new String();
	String[] urls;
	Integer Id;
	
	//メンバー情報更新権限
	@Override
	protected boolean isHavePower() {
		
		boolean flag = false;
		if (!loginLeadersDto.memberUpdate) {
			//遷移先が自分の情報であればtrue
			warUrl = RequestUtil.getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
			urls = warUrl.split("/",5);
			Id = Integer.valueOf(urls[4]);
			if (loginLeadersDto.memberId.equals(Id)) {
				flag = true;
			}
		}else{
			flag = true;
		}
		return flag;
	}
}
