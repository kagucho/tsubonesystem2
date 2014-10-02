package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.form.ClubForm;

public class LeadersClubUpdatePowerInterceptor extends LeadersPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	@Resource
	protected ClubForm clubForm;
	
	//部情報更新権限
	@Override
	protected boolean isHavePower() {
		boolean flag = false;
		//遷移先が自分の担当部であればtrue
		if (loginLeadersDto.memberId.equals(clubForm.tLeaders.tMember.id)) {
			flag = true;
		}
		return flag;
	}
}
