
package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.util.TokenProcessor;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.action.abstracts.PartyOperateAbstractAction;
import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.entity.TPartyClub;

public class PartyRegistAction extends PartyOperateAbstractAction{
	
	/** アクションネーム */
	public String actionName = "PartyRegist";
	
	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	boolean disabledFlag = false;
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		
		/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        partyForm.mailSendFlag = false;
        
        //keyをclubId, valueをclubNameとしてマップを作成する
        partyForm.clubMapSS = getClubMap();
         
        return viewinput();
	}
	
	//confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyInput.jsp";
    }
    
	@Execute(validator = true, input = "partyInput.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput") 
	public String confirm() {
    		return super.confirm();
	}
    
	@Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	TParty party = getTPartyForDb(loginAdminDto.memberId);
        	//DBに追加
        	tPartyService.insert(party);
        	
        	//完了画面から詳細画面遷移のためにIDを取得
        	partyForm.id = party.id;
        	
        	//obを含めるかどうか
        	boolean containsOb = partyForm.ObAttendFlag;
        	
        	//参加対象な部が選択されていたらTPartyClubにレコードを挿入する
        	if (partyForm.attendClub != null) {
        		//PartyClubテーブルにレコードを挿入する
        		insertTPartyClub(party.id);
        	}
        	
        	//PartyAttendテーブルに未回答として対象者を登録する
        	insertPartyAttend(getTMemberForDb(containsOb), party.id);
        	
        	//メール送信の必要があれば送信する
        	if (partyForm.mailSendFlag) {
        		sendMail(this.partyForm, loginAdminDto.memberId);
        	}
	        return "partyComplete.jsp";	
        } else {
        	return "/common/error.jsp";
        }
	}

	/**
	 * 対象のパーティに参加対象のTMemberを取得する
	 * @param containsOb
	 * @return
	 */
	protected Collection<TMember> getTMemberForDb(boolean containsOb) {
		Collection<TMember> tMemberList;
		if (partyForm.attendClub != null) {
			tMemberList = new HashSet<TMember>();
			HashSet<TMember> tMemberListSecond = new HashSet<TMember>();
			//部ごとに会員をセットする
			for (String cLubId : partyForm.attendClub) {
				List<TMemberClub> tMemberClubList = tMemberClubService.findByClubId(cLubId, containsOb);
				tMemberListSecond.addAll(getTMemberByTMemberClubList(tMemberClubList));
			}
			//重複をなくす処理	
			HashSet<Integer> set = new HashSet<Integer>();
			for(TMember one : tMemberListSecond){
				set.add(one.id);
			}
			for(Integer one : set){
				tMemberList.add(tMemberService.findById(one));
			}
		} else {
			tMemberList = tMemberService.findAllOrderById(containsOb);
		}
		return tMemberList;
	}

	/**
	 * PartyAttendに未回答として登録する
	 * @param tMemberList
	 * @param partyId
	 */
	protected void insertPartyAttend(Collection<TMember> tMemberList, Integer partyId) {
		for (TMember tMemberOne : tMemberList) {
    		TPartyAttend tPartyAttend = new TPartyAttend();
    		tPartyAttend.memberId = tMemberOne.id;
    		tPartyAttend.partyId = partyId;
    		tPartyAttend.attend = Integer.valueOf(PartyAttendCode.UNSUBMITTED.getCode());
    		tPartyAttendService.insert(tPartyAttend);
    	}
	}

	/**
	 * PartClubテーブルに情報を挿入する
	 * @param partyId
	 */
    protected void insertTPartyClub(Integer partyId) {
    	if (partyForm.attendClub == null) {
    		return;
    	}
    	for (String clubId : partyForm.attendClub) {
    		TPartyClub tPartyClub = new TPartyClub();
			tPartyClub.PartyId = partyId;
			tPartyClub.ClubId = Integer.valueOf(clubId);
			tPartyClubService.insert(tPartyClub);
    	}
	}
}
