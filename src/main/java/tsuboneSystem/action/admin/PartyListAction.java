package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.form.PartyListForm;
import tsuboneSystem.service.TPartyService;

public class PartyListAction {
	
	public String actionName = "PartyList";
	
	public String actionNameSub = "定例会、飲み会、夏合宿、その他行事、麻雀大会まで、部で行う様々なイベントを登録しよう。";
	
	/** PartyListのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyListForm partyListForm;
	
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
	
    @Execute(validator = false)
    @RemoveSession(name = "partyDto")
	public String index() {
    	
    	//日時を取得する
    	Date dateNow = new Date();
		
		//期限付きで出欠中の会議
		partyListForm.tPartyAttendNow = tPartyService.findBy_Deadline_GE_Now(dateNow, getLoginMemberId());
		
		//期限なしで出欠中の会議(開始日時は設定されている)
		partyListForm.tPartyNoAttendNow = tPartyService.findBy_NODeadline_GE(dateNow, getLoginMemberId(), false);
		
		//開催中の会議
		partyListForm.tPartySessionNow = tPartyService.findBy_MeetingDay_BETWEEN_Now(dateNow, getLoginMemberId());
		
		//開催待ちの会議
		partyListForm.tPartySessionWill = tPartyService.findBy_WillMeeting(dateNow, getLoginMemberId());
		
		//期限なし、かつ開催日も設定されていない会議は構想段階の会議とする
		partyListForm.tPartyIdea = tPartyService.findBy_NODeadline_GE(dateNow, getLoginMemberId(), true);
	
        return "partyList.jsp";
	}
    
    //過去の会議一覧
    @Execute(validator = false)
    public String historyList(){
    	
    	//日時を取得する
    	Date dateNow = new Date();

    	partyListForm.tPartyHistory = new ArrayList<TParty>();
    	
    	//開催日が設定されず、一ヶ月過ぎた会議
    	partyListForm.tPartyHistory.addAll(tPartyService.findBy_NODeadline_LE(dateNow, getLoginMemberId()));
    	
    	//その他開催日も終了日も過ぎた会議
    	partyListForm.tPartyHistory.addAll(tPartyService.findByOldParty(dateNow, getLoginMemberId()));
    	
		return "partyHistoryList.jsp";
    }
    
    /**
     * ログイン者のIDを取得するカットポイント
     * @return
     */
    protected Integer getLoginMemberId() {
    	return loginAdminDto.memberId;
    }
}
