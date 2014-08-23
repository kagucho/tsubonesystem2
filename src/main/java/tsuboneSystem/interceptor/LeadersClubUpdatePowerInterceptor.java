package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginLeadersDto;

public class LeadersClubUpdatePowerInterceptor extends LeadersPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	//部情報更新権限
	@Override
	protected boolean isHavePower() {
		return (loginLeadersDto.clubUpdate);
	}
}
