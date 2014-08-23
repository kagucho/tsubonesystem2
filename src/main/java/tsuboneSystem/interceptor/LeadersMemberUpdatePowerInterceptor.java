package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginLeadersDto;

public class LeadersMemberUpdatePowerInterceptor extends LeadersPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	//メンバー情報更新権限
	@Override
	protected boolean isHavePower() {
		return (loginLeadersDto.memberUpdate);
	}
}
