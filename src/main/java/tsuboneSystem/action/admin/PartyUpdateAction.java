package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.action.abstracts.PartyOperateAbstractAction;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyClub;

public class PartyUpdateAction extends PartyOperateAbstractAction{
	
	/** アクションネーム */
	public String actionName = "PartyUpdate";
	
	/** LoginAdminDtoのDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	boolean disabledFlag = true;
	
	@Execute(validator = false, reset = "resetInput", urlPattern = "{id}")
	public String input() {
		
		//２重登録防止の為Token作成
        TokenProcessor.getInstance().saveToken(request);

		TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
    	
        //登録されている部をすべてリストの形で呼び出す
        partyForm.clubList = tClubService.findAllOrderById();
        
        //マップを作る。形はkey(数値)とvalue(名称)の２個セットの形
        partyForm.clubMapSS = tClubService.getClubMap();
        
        int i = 0;
        partyForm.attendClub = new String[party.tPartyClubList.size()];
        for (TPartyClub tPartyClub : party.tPartyClubList) {
        	partyForm.attendClub[i] = tPartyClub.ClubId.toString();
        	i++;
        }
		
        return viewinput();
	}
	
    @Execute(validator = false)
	public String complete() {
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {

	    	TParty updateParty = new TParty(getLoginMemberId(), partyForm);
	    	//DBを更新する
	    	tPartyService.update(updateParty);
	    	
	    	//メールの送信があれば送信する
	        if (partyForm.mailSendFlag) {
	        	sendMail(partyForm, getLoginMemberId());
	        }
				return "partyComplete.jsp";
        } else {
        	return "/common/error.jsp";
        }
	}
    

	@Override
	protected Integer getLoginMemberId() {
		return loginAdminDto.memberId;
	}
}
