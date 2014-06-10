package tsuboneSystem.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.PartyAttendForm;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyService;

public class PartyAttendUpdateAction {
	
	public String actionName = "PartyAttendUpdate";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyAttendForm partyAttendForm;
	
	/** TPartyのサービスクラス */
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
	
	/** 会議の一覧 */
	public List<TParty> partyItem;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}")
    @RemoveSession(name="partyAttendForm")
	public String on() {
    	switch (partyAttendForm.id) {
		case 1:
			partyAttendForm.tMemberOn = partyDto.tMemberOn;
			partyAttendForm.btn1 = "欠席に変更する";
			partyAttendForm.btn1Key = "1";
			partyAttendForm.btn2 = "未回答に変更する";
			partyAttendForm.btn2Key = "2";
			break;
		case 2:
			partyAttendForm.tMemberOff = partyDto.tMemberOff;
			partyAttendForm.btn1 = "出席に変更する";
			partyAttendForm.btn1Key = "3";
			partyAttendForm.btn2 = "未回答に変更する";
			partyAttendForm.btn2Key = "2";
			break;
		case 3:
			partyAttendForm.mapKuzuSS = partyDto.mapKuzuSS;
			partyAttendForm.btn1 = "出席に変更する";
			partyAttendForm.btn1Key = "3";
			partyAttendForm.btn2 = "欠席に変更する";
			partyAttendForm.btn2Key = "1";
			break;
		}
    	
        return "partyAttendUpdateInput.jsp";
	}
    
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "confirm/{id}")
	public String confirm() {
    	
    	switch (partyAttendForm.id) {
    	//現状から欠席に移行する
		case 1:
			for (String offMemberId : partyAttendForm.offCheck) {
				TPartyAttend tPartyAttendNew = tPartyAttendService.findByMemberIdWithPartyId(offMemberId, partyDto.id);
				//未回答からの移動の場合は該当がないので新規にエンティティを作成
				if (tPartyAttendNew == null){
					TPartyAttend tPartyAttend = new TPartyAttend();
					tPartyAttend.memberId = Integer.valueOf(offMemberId);
					tPartyAttend.partyId = partyDto.id;
					tPartyAttendNew = tPartyAttend;
				}
				tPartyAttendNew.attend = false;
				partyAttendForm.tPartyAttendNew.add(tPartyAttendNew);
				partyAttendForm.tMemberNew.add(tMemberService.findById(Integer.valueOf(offMemberId)));
			}	
			break;
		//現状から未回答に移行する
		case 2:
			for (String noAttendMemberIdOne : partyAttendForm.offCheck){
				TPartyAttend tPartyAttendNew = tPartyAttendService.findByMemberIdWithPartyId(noAttendMemberIdOne, partyDto.id);
				partyAttendForm.tPartyAttendNewNo.add(tPartyAttendNew);
				partyAttendForm.tMemberNew.add(tMemberService.findById(Integer.valueOf(noAttendMemberIdOne)));
			}
			
			break;
		//現状から出席に移行する
		case 3:
			for (String onMemberId : partyAttendForm.offCheck) {
				TPartyAttend tPartyAttendNew = tPartyAttendService.findByMemberIdWithPartyId(onMemberId, partyDto.id);
				//未回答からの移動の場合は該当がないので新規にエンティティを作成
				if (tPartyAttendNew == null){
					TPartyAttend tPartyAttend = new TPartyAttend();
					tPartyAttend.memberId = Integer.valueOf(onMemberId);
					tPartyAttend.partyId = partyDto.id;
					tPartyAttendNew = tPartyAttend;
				}
				tPartyAttendNew.attend = true;
				partyAttendForm.tPartyAttendNew.add(tPartyAttendNew);
				partyAttendForm.tMemberNew.add(tMemberService.findById(Integer.valueOf(onMemberId)));
			}
			break;
		}	
    	return "partyAttendUpdateConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	if (partyAttendForm.tPartyAttendNew.size() > 0) {
    		for (TPartyAttend tPartyAttendNewOne : partyAttendForm.tPartyAttendNew) {
    			//未回答からの出席、欠席は新規追加になる。
    			if (tPartyAttendNewOne.id == null){
    				tPartyAttendService.insert(tPartyAttendNewOne);
    			}else{
    				tPartyAttendService.update(tPartyAttendNewOne);
    			}
    		} 
    	}else if (partyAttendForm.tPartyAttendNewNo.size() > 0) {
    		for (TPartyAttend tPartyAttendNewNoOne : partyAttendForm.tPartyAttendNewNo) {
        		tPartyAttendService.delete(tPartyAttendNewNoOne);
    		}
    	}
        return "partyAttendUpdateComplete.jsp";
	}
    
}
