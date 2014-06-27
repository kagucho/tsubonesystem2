package tsuboneSystem.action.admin;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class PartyDetailAction {
	
	public String actionName = "PartyDetail";
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService  tPartyClubService;
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	
    	TParty party = tPartyService.findById(partyForm.id);
    	Beans.copy(party, partyForm).execute();
    	Beans.copy(party, partyDto).execute();
    	
    	 int i = 0;
         partyForm.attendClub = new String[party.tPartyClubList.size()];
         partyDto.attendClub = new String[party.tPartyClubList.size()];
         for (TPartyClub tPartyClubOne : party.tPartyClubList) {
         	partyForm.attendClub[i] = tPartyClubOne.ClubId.toString();
         	partyDto.attendClub[i] = tPartyClubOne.ClubId.toString();
         	i++;
         }
    	
    	//現在時刻を取得し、期限内か判断する
       	Date dateNow = new Date();
        partyForm.deadFlag = partyDto.deadFlag(party, dateNow);
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        partyForm.clubMapIS = tClubService.getClubMapIS();
    	
        return "partyDetail.jsp";
	}
}
