package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.util.RequestUtil;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.service.TClubService;

public class LeadersClubUpdatePowerInterceptor extends LeadersPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	String warUrl = new String();
	String[] urls;
	Integer Id;
	
	//部情報更新権限
	@Override
	protected boolean isHavePower() {
		
		boolean flag = false;
		if (!loginLeadersDto.memberUpdate) {
			//遷移先が自分の担当部であればtrue
			warUrl = RequestUtil.getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
			urls = warUrl.split("/",5);
			Id = Integer.valueOf(urls[4]);
			TClubService tClubService = SingletonS2Container.getComponent(TClubService.class);
			TClub tClub = tClubService.findById(Id);
			if (loginLeadersDto.memberId.equals(tClub.tLeaders.tMember.id)) {
				flag = true;
			}
		}else{
			flag = true;
		}
		
		return flag;
	}
}
