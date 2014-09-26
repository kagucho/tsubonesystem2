
package tsuboneSystem.action.admin;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.action.abstracts.PartyOperateAbstractAction;
import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;

public class PartyRegistAction extends PartyOperateAbstractAction{
	
	/** アクションネーム */
	public String actionName = "PartyRegist";
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDto */
	@Resource
	public LoginAdminDto loginAdminDto;
	
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		
		/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        partyForm.mailSendFlag = false;
        partyForm.disabledFlag = false;
        
        //keyをclubId, valueをclubNameとしてマップを作成する
        partyForm.clubMapSS = tClubService.getClubMap();
         
        return viewinput();
	}
    
	@Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	//会議情報をpartyテーブルに追加
        	TParty party = tPartyService.insertCustom(loginMemberDto.memberId, partyForm);
        	
        	//完了画面から詳細画面遷移のためにIDを取得
        	partyForm.id = party.id;
        	
        	//対象にobを含めるかどうか
        	boolean containsOb = StringUtil.isNotEmpty(partyForm.ObAttendFlag);
        	
        	//参加対象な部が選択されていたらTPartyClubにレコードを挿入する
        	if (partyForm.attendClub != null) {
        		//PartyClubテーブルにレコードを挿入する
        		insertTPartyClub(party.id);
        	}
        	
        	//PartyAttendテーブルに未回答として対象者を登録する
        	insertPartyAttend(getTMemberForDb(containsOb), party.id);
        	
        	//メール送信の必要があれば送信する
        	if (partyForm.mailSendFlag) {
        		sendMail(partyForm, getLoginMemberId());
        	}
	        return "partyComplete.jsp";	
        } else {
        	return "/common/error.jsp";
        }
	}

	protected Integer getLoginMemberId() {
		return loginAdminDto.memberId;
	}

	/**
	 * 対象のパーティに参加対象のTMemberを取得する
	 * @param containsOb
	 * @return
	 */
	protected List<TMember> getTMemberForDb(boolean containsOb) {
		if (partyForm.attendClub != null) {
			return tMemberService.findByClubIds(containsOb, partyForm.attendClub);
		} else {
			return tMemberService.findAllOrderById(containsOb);
		}
	}

	/**
	 * PartyAttendに未回答として登録する
	 * @param tMemberList
	 * @param partyId
	 */
	protected void insertPartyAttend(Collection<TMember> tMemberList, Integer partyId) {
		for (TMember tMember : tMemberList) {
    		TPartyAttend tPartyAttend = new TPartyAttend();
    		tPartyAttend.memberId = tMember.id;
    		tPartyAttend.partyId = partyId;
    		tPartyAttend.attend = Integer.valueOf(PartyAttendCode.UNSUBMITTED.getCode());
    		tPartyAttendService.insert(tPartyAttend);
    	}
	}
}
