package tsuboneSystem.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.AttendForm;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyService;

public class AttendAction {
	
	public String actionName = "PartyAttendUpdate";
	
	/** AttendFormのアクションフォーム */
	@ActionForm
	@Resource
	protected AttendForm attendForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDtoのサービスクラス */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** PartyDtoのサービスクラス */
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
	
    
    @Execute(validator = false)
	public String yes() {
    	
    	//出席
    	attendForm.attendFlag =  Integer.valueOf(PartyAttendCode.YES_ATTEND.getCode());
    	attendForm.meetingName = partyDto.meetingName;
    	attendForm.attendMessege = "出席する";
    			
    	return "AttendConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String no() {
    	
    	//欠席
    	attendForm.attendFlag =  Integer.valueOf(PartyAttendCode.NO_ATTEND.getCode());
    	attendForm.meetingName = partyDto.meetingName;
    	attendForm.attendMessege = "欠席する";
    	
    	return "AttendConfirm.jsp";
	}
    
	@Execute(validator = false)
   	public String complete() {
    	
       	
      //すでに出欠席が登録されている場合にはアップデートする。(されていない場合は出席対象外の人間)
    	TPartyAttend tPartyAttendOld = tPartyAttendService.findByMemberIdWithPartyId(loginAdminDto.memberId.toString(), partyDto.id);
    	if ( tPartyAttendOld == null) {
    		TPartyAttend tPartyAttend = new TPartyAttend();
           	tPartyAttend.memberId = loginAdminDto.memberId;
           	tPartyAttend.partyId = partyDto.id;
           	tPartyAttend.attend = attendForm.attendFlag;
           	tPartyAttendService.insert(tPartyAttend);
    	} else {
        	tPartyAttendOld.memberId = loginAdminDto.memberId;
        	tPartyAttendOld.partyId = partyDto.id;
        	tPartyAttendOld.attend = attendForm.attendFlag;
           	tPartyAttendService.update(tPartyAttendOld);
    	}
   	
    return "AttendComplete.jsp";
   	}
    
}
