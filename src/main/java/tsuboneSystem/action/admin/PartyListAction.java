package tsuboneSystem.action.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.service.TPartyService;

public class PartyListAction {
	
	public String actionName = "PartyList";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
	/** partyDto */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** 会議の一覧(期限内) */
	public List<TParty> partyItemOn;
	
	/** 会議の一覧(期限外) */
	public List<TParty> partyItemOff;
	
    @Execute(validator = false)
    @RemoveSession(name = "partyDto")
	public String index() {
    	
    	//日時を取得する
    	Date dateNow = new Date();
    	
    	//期限内
    	partyItemOn = tPartyService.findBy_Deadline_GE_Now(dateNow);
    	
    	//期限外
    	partyItemOff = tPartyService.findBy_Deadline_LE_Now(dateNow);
    	
        return "partyList.jsp";
	}
}
