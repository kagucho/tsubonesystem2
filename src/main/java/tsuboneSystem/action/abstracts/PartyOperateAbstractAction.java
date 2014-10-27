package tsuboneSystem.action.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.entity.TPartySendMail;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.original.util.MailManagerUtil;
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
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
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
	
    @Execute(validator = true, input = "viewinput", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
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
    	
    	StringBuffer bf = new StringBuffer();
    	bf.append(partyForm.content);
    	bf.append("\n");
    	bf.append("\n");
    	bf.append("このイベントの詳細はこちらから");
    	partyForm.content = new String(bf);
    	
    	//メールを送信する
    	MailManagerUtil mailUtil = new MailManagerUtil();
    	mailUtil.setRegistId(loginMemberDto.memberId);
    	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
    	mailUtil.setTitle(partyForm.title);
    	mailUtil.setContent(partyForm.content);	
    	mailUtil.setContentId(partyForm.id);
    	mailUtil.setContentName("partyDetail/detail");
    	mailUtil.setLinkUrlFlag(true);
    	mailUtil.setToAddressActorSplit(partyForm.tMemberSendList);
    	mailUtil.sendMail();

    	//会議とメールを紐付けるDBに登録する
    	TPartySendMail tPartySendMail = new TPartySendMail();
    	tPartySendMail.PartyId = partyForm.id;
    	tPartySendMail.MailId = mailUtil.getTMail().id;
    	tPartySendMailService.insert(tPartySendMail);
    	
    	return mailUtil.getSendMailResult();	
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
