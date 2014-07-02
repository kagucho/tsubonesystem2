package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TMember;


public class PartyDetailAction extends tsuboneSystem.action.admin.PartyDetailAction {
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	//自分が主催の会議ならTRUE
	public boolean myPartyFlag = false;
	
	@Override
	protected Integer getLoginMemberId() {
		return loginIndividualsDto.memberId;
	}
	
	@Override
	protected TMember getLoginTMember() {
		return loginIndividualsDto.tMemberLogin;
	}
	
	@Override
	public String index() {
		String nextPage = super.index();
		
		//もし自分が主催の会議ならフラグをTRUEにする
		if (partyForm.creatorId.equals(getLoginMemberId())) {
			myPartyFlag = true;
		}
		
		return nextPage;
	}
}
