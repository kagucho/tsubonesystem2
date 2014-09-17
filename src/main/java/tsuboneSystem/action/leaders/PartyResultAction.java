package tsuboneSystem.action.leaders;

import tsuboneSystem.entity.TParty;

public class PartyResultAction extends tsuboneSystem.action.admin.PartyResultAction{
	
	@Override
	public String input() {
		TParty tParty = tPartyService.findById(partyForm.id);
		if (tParty == null) {
			return "/common/error.jsp";
		}else if(!tParty.creatorId.equals(loginMemberDto.memberId)){
			//対象の会議の制作者ではない
			if(tParty.resultEditMemberId != null){
				//すでに結果が入力されている場合
				if(tParty.resultEditEndFlag && !tParty.resultEditMemberId.equals(loginMemberDto.memberId)){
					//以後の編集が禁止になっていて、最後に編集した人ではないとき
					return "/common/error.jsp";
				}
			}
		}
		return super.input();
	}
}
