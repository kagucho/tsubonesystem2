package tsuboneSystem.action.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TPartyService;

public class PartyDeleteAction {
	
	
	public String actionName = "PartyDelete";
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDtoのDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	@Execute(validator = false, urlPattern = "{id}")
	public String input() {
		
		TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
		
        return "partyConfirm.jsp";
	}
    
    @Execute(validator = false)
	public String complete() {
    	
    	TParty partyDelete = new TParty();
    	Beans.copy(partyForm, partyDelete).excludes("meetingDay","meetingTime","meetingDeadlineDay","meetingDeadlineTime").execute();
    	//日付と日時をString型からDate型に変換
    	try {
    		if(!StringUtils.isEmpty(partyForm.meetingDay)){
    			Date meetingDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDay.toString()).getTime());
    			partyDelete.meetingDay = meetingDay;
    		}
    		
    		if (!StringUtils.isEmpty(partyForm.meetingTime)) {
    			Date meetingTime = new Date(new SimpleDateFormat("HH:mm").parse(partyForm.meetingTime.toString()).getTime());
    			partyDelete.meetingTime = meetingTime;
    		}
    		
			if(!StringUtils.isEmpty(partyForm.meetingDeadlineDay)){
				Date meetingDeadlineDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDeadlineDay.toString()).getTime());
				partyDelete.meetingDeadlineDay = meetingDeadlineDay;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	partyDelete.deleteFlag = Boolean.valueOf(true);
    	tPartyService.update(partyDelete);
    	
        return "partyComplete.jsp";
	}
    
    public Integer getLoginMemberId() {
    	return loginAdminDto.memberId;
    }
}
