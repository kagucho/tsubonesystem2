/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    	if (tPartyList.size() > 0) {
    		for (TParty tParty : tPartyList) {
    			calList.add(new FullCalenderDto(tParty));
    		}
    	}
    	
    	String json = JSON.encode(calList);
    	
    	ResponseUtil.write(json, "application/json");
    	
        return null;
	}

}
