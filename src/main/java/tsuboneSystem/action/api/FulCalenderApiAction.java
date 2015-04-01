package tsuboneSystem.action.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.arnx.jsonic.JSON;

import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import tsuboneSystem.dto.FullCalenderDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.service.TPartyService;

public class FulCalenderApiAction {

	@Resource
	public TPartyService tPartyService;
	
    @Execute(validator = false)
	public String getJson() {
    	
    	List<TParty> tPartyList = tPartyService.findAllForCalender();
    	List<FullCalenderDto> calList = new ArrayList<FullCalenderDto>();
    	if (calList.size() > 0) {
    		for (TParty tParty : tPartyList) {
    			calList.add(new FullCalenderDto(tParty));
    		}
    	}
    	
    	String json = JSON.encode(calList);
    	
    	ResponseUtil.write(json, "application/json");
    	
        return null;
	}

}
