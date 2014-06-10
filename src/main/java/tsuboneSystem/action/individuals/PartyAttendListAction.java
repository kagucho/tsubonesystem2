package tsuboneSystem.action.individuals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.form.PartyAttendForm;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class PartyAttendListAction {
	
	public String actionName = "PartyAttend";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyAttendForm partyAttendForm;
	
	/** partyDto */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** 会議の一覧 */
	public List<TParty> partyItem;
	
	public boolean deadFlag;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}")
    @RemoveSession(name = "partyAttendForm")
	public String index() {
    	
    	TParty party = tPartyService.findById(partyAttendForm.id);
    	Beans.copy(party, partyDto).execute();
    	
    	//現在時刻を取得し、期限内か判断する
    	Date dateNow = new Date();
    	deadFlag = partyDto.deadFlag(party, dateNow);
	    	
    	//全員のリストを取得し、以下の処理の中で該当のメンバーは削除してき、残ったメンバーが出欠席を返さないクズ。
    	if (party.ObAttendFlag){
    		//OBの出席を含む
    		if (party.tPartyClubList.size() > 0) {
    			//部によって出席対象が絞られている場合
    			List<TPartyClub> tPartyClub = tPartyClubService.findByPartyId(party.id);
    			Set<TMember> tMemberSet = new HashSet<TMember>();
    			for (TPartyClub tPartyClubOne : tPartyClub) {
    				for (TMemberClub tMemberClubOne : tPartyClubOne.tClub.tMemberClubList) {
    					tMemberSet.add(tMemberClubOne.tMember);
        				}
    			}
    			partyAttendForm.tMemberKuzu = new ArrayList<TMember>();
    			for (TMember tMember : tMemberSet) {
    				partyAttendForm.tMemberKuzu.add(tMember);
    			}
    		}else{
    			//全員が出席対象
    			partyAttendForm.tMemberKuzu = tMemberService.findByIdAll();
    		}	
    	}else{
    		//OBの出席を含まない
    		if (party.tPartyClubList.size() > 0) {
    			//部によって出席対象が絞られている場合
    			List<TPartyClub> tPartyClub = tPartyClubService.findByPartyIdNoOB(party.id);
    			Set<TMember> tMemberSet = new HashSet<TMember>();
    			for (TPartyClub tPartyClubOne : tPartyClub) {
    				for (TMemberClub tMemberClubOne : tPartyClubOne.tClub.tMemberClubList) {
    					tMemberSet.add(tMemberClubOne.tMember);
        				}
    			}
    			partyAttendForm.tMemberKuzu = new ArrayList<TMember>();
    			for (TMember tMember : tMemberSet) {
    				partyAttendForm.tMemberKuzu.add(tMember);
    			}
    		}else{
    			//全員が出席対象
    			partyAttendForm.tMemberKuzu = tMemberService.findByIdNoOBAll();
    		}	
    	}

    	partyAttendForm.mapKuzuSS = new HashMap<String,String>();
    	partyDto.mapKuzuSS = new HashMap<String,String>();
    	for (TMember memberKuzuOne : partyAttendForm.tMemberKuzu) {
    		partyAttendForm.mapKuzuSS.put(memberKuzuOne.id.toString(), memberKuzuOne.hname);
    		partyDto.mapKuzuSS.put(memberKuzuOne.id.toString(), memberKuzuOne.hname);
    	}
    	
    	//出席している人のリスト
    	partyAttendForm.tAttendOn = tPartyAttendService.findByPartyIdAndAttendOn(partyAttendForm.id);
    	for (TPartyAttend attendOn : partyAttendForm.tAttendOn){
    		partyAttendForm.tMemberOn.add(tMemberService.findById(attendOn.memberId));
    		partyAttendForm.mapKuzuSS.remove(attendOn.memberId.toString());
    		partyDto.mapKuzuSS.remove(attendOn.memberId.toString());
    	}
    	
    	//欠席している人のリスト
    	partyAttendForm.tAttendOn = tPartyAttendService.findByPartyIdAndAttendOff(partyAttendForm.id);
    	for (TPartyAttend attendOff : partyAttendForm.tAttendOn){
    		partyAttendForm.tMemberOff.add(tMemberService.findById(attendOff.memberId));
    		partyAttendForm.mapKuzuSS.remove(attendOff.memberId.toString());
    		partyDto.mapKuzuSS.remove(attendOff.memberId.toString());
    	}
    	
    	//partyDtoの出欠席リストにもコピーしておく(更新や削除のActionなどで使用する。)
    	partyDto.tMemberOn = partyAttendForm.tMemberOn;
    	partyDto.tMemberOff = partyAttendForm.tMemberOff;
    	
        return "partyAttendList.jsp";
	}
    
}
