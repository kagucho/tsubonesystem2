package tsuboneSystem.interceptor;

import javax.annotation.Resource;

import tsuboneSystem.dto.PartyDto;

public class IndividualsMyPartyPowerInterceptor extends IndividualsPowerAbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	@Resource
	protected PartyDto partyDto;
	
	//自分で作成した会議かどうか
	@Override
	protected boolean isHavePower() {
		return (partyDto.myPartyFlag);
	}
}
