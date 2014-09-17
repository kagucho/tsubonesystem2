package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginMemberDto;

public class PartyListAction extends tsuboneSystem.action.admin.PartyListAction{
	
	@Resource
	LoginMemberDto LoginMemberDto;
    
    /**
     * ログイン者のIDを取得するカットポイント
     * @return
     */
	@Override
    protected Integer getLoginMemberId() {
    	return LoginMemberDto.memberId;
    }
}
