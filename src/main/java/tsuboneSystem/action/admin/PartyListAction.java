package tsuboneSystem.action.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
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
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** LoginAdminDto用のDto */
	@Resource
	LoginAdminDto loginAdminDto;
	
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
    	
    	//締め切りが指定されていて、まだ締め切りが過ぎていない会議
    	partyItemOn = tPartyService.findBy_Deadline_GE_Now(dateNow, getLoginMemberId());
    	
    	//締め切りが設定されず、作成日から一ヶ月以内の会議
		List<TParty> partyItemNOMeetingDay = tPartyService.findBy_NODeadline_GE(dateNow, getLoginMemberId());
		if (partyItemNOMeetingDay.size() != 0 ) {
			partyItemOn.addAll(partyItemNOMeetingDay);
		}
    	
    	//締め切りが指定されていて、もう締め切りが過ぎている会議
    	partyItemOff = tPartyService.findBy_Deadline_LE_Now(dateNow, getLoginMemberId());
    	
		//締め切りが設定されず、作成日から一ヶ月以上たった会議
		List<TParty> tPartyNOmeetingDay = tPartyService.findBy_NODeadline_LE(dateNow, getLoginMemberId());
		if (tPartyNOmeetingDay.size() != 0 ) {
			partyItemOff.addAll(tPartyNOmeetingDay);
		}
	
        return "partyList.jsp";
	}
    
    /**
     * ログイン者のIDを取得するカットポイント
     * @return
     */
    protected Integer getLoginMemberId() {
    	return loginAdminDto.memberId;
    }
}
