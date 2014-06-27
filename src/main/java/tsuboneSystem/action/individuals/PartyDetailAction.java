package tsuboneSystem.action.individuals;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TPartyService;

public class PartyDetailAction {
	
	public String actionName = "PartyDetail";
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** Memberのアクションフォーム */
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
    	
    	//現在時刻を取得し、期限内か判断する
    	Date dateNow = new Date();
    	partyForm.deadFlag = partyDto.deadFlag(party, dateNow);
    	
        return "partyDetail.jsp";
	}
}
