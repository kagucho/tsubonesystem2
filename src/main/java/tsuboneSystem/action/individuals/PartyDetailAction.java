package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;


public class PartyDetailAction extends tsuboneSystem.action.admin.PartyDetailAction {
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Resource
	public LoginMemberDto loginMemberDto;
	
	//サブメッセージ
	public String actionNameSub = null;
	
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
	
	@Execute(validator = false, urlPattern = "detail/{id}", reset = "resetInput")
	public String index() {
		String nextPage = super.index();
		
		//もし自分が主催の会議ならフラグをTRUEにする
		if (partyForm.creatorId.equals(getLoginMemberId())) {
			myPartyFlag = true;
			partyDto.myPartyFlag = myPartyFlag;
			actionNameSub = "この会議の作成者はあなたです。";
		}else{
			myPartyFlag = false;
			partyDto.myPartyFlag = myPartyFlag;
			actionNameSub = null;
		}
		
		return nextPage;
	}
}
