package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.form.MemberForm;

public class LeadersMemberUpdatePowerInterceptor extends LeadersPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected LoginLeadersDto loginLeadersDto;
	
	@Resource
	protected MemberForm memberForm;
	
	//メンバー情報更新権限
	@Override
	protected boolean isHavePower() {
		
		boolean flag = false;
		if (!loginLeadersDto.memberUpdate) {
			//遷移先が自分の情報であればtrue
			if (loginLeadersDto.memberId.equals(memberForm.id)) {
				flag = true;
			}
		}else{
			flag = true;
		}
		return flag;
	}
}
