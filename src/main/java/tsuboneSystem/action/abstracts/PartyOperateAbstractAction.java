package tsuboneSystem.action.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.entity.TPartySendMail;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartySendMailService;
import tsuboneSystem.service.TPartyService;

public abstract class PartyOperateAbstractAction {
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** tMailServiceのサービスクラス */
	@Resource
	protected TPartySendMailService tPartySendMailService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
    @Execute(validator = true, input = "partyInput.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
	public String confirm() {
    	// メールを送る場合は送信対象者をリストに格納する
    	if (partyForm.mailSendFlag) {
			// OBを含めるかどうか
			boolean containsOb = (partyForm.mailSendOBFlag != null);

			// 全員にメールが送られる場合
			if (partyForm.mailSendAllFlag != null) {
				partyForm.tMemberSendList = tMemberService.findAllOrderById_ForMail(containsOb);
			//部ごとにメールが送られる場合
			} else if (partyForm.clubListCheck != null) {
				partyForm.tMemberSendList = tMemberService.findByClubIds(containsOb, partyForm.clubListCheck);
			}
    	}   
    	return "partyConfirm.jsp";
	}
	
	/**
	 * TMemberClubリストからTMemberのリストを取得
	 * @param tMemberClubList
	 * @return
	 */
	protected ArrayList<TMember> getTMemberByTMemberClubList(List<TMemberClub> tMemberClubList) {
		ArrayList<TMember> rtnList = new ArrayList<TMember>();
		for (TMemberClub tMemberClub : tMemberClubList) {
			rtnList.add(tMemberClub.tMember);
		}
		return rtnList;
	}
	
	abstract public String complete();
	/**
	 * メールの配信処理
	 * @param partyForm
	 * @param registMemberId
	 * @return
	 */
	protected boolean sendMail(PartyForm partyForm, int registMemberId) {
    	
    	//メールの送信者のID
    	partyForm.registMemberId = Integer.valueOf(registMemberId);
    	
    	//メールを送信する
    	MailManager manager = new MailManager();
    	manager.setTitle(partyForm.title);
    	manager.setContent(partyForm.content);
    	manager.setToAddress(partyForm.tMemberSendList.toArray(new TMember[0]));
    	//DBにログを残す
    	manager.setLogFlg(true, registMemberId, tMailSendMemberService, tMailService);
    	if (!manager.sendMail()) {
    		//TODO　送信失敗時に処理
    	}   
    	//会議とメールを紐付けるDBに登録する
    	TPartySendMail tPartySendMail = new TPartySendMail();
    	tPartySendMail.PartyId = partyForm.id;
    	tPartySendMail.MailId = manager.getTMail().id;
    	tPartySendMailService.insert(tPartySendMail);
    	
    	return manager.getTMail().errorFlag;	
    }
	
	/**
	 * PartClubテーブルに情報を挿入する
	 * @param partyId
	 */
    protected void insertTPartyClub(Integer partyId) {
    	if (partyForm.attendClub == null) {
    		return;
    	}
    	for (String clubId : partyForm.attendClub) {
    		TPartyClub tPartyClub = new TPartyClub();
			tPartyClub.PartyId = partyId;
			tPartyClub.ClubId = Integer.valueOf(clubId);
			tPartyClubService.insert(tPartyClub);
    	}
	}
    
    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyInput.jsp";
    }
	
	abstract protected Integer getLoginMemberId();
}
