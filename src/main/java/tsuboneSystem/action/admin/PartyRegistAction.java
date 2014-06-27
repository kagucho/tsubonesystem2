
package tsuboneSystem.action.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
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

public class PartyRegistAction {
	
	/** アクションネーム */
	public String actionName = "PartyRegist";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** PartyFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyForm partyForm;
	
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
	
	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	public TParty tParty = new TParty();
	
	boolean disabledFlag = false;
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		
		/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        partyForm.mailSendFlag = false;
        
        //keyをclubId, valueをclubNameとしてマップを作成する
        partyForm.clubMapSS = new HashMap<String,String>();
        for ( TClub club : tClubService.findAllOrderById()) {
        	partyForm.clubMapSS.put(club.id.toString(), club.ClubName);
        }
         
        return viewinput();
	}
	
	//confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyInput.jsp";
    }
    
	@Execute(validator = true, input = "partyInput.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput") 
	public String confirm() {
    	
		//メールを送る場合は送信対象者をリストに格納する
    	if (partyForm.mailSendFlag) {
    		//OBを含めるかどうか
    		boolean containsOB = (partyForm.mailSendOBFlag != null);
    		
    		//全員にメールが送られる場合
    		if (partyForm.mailSendAllFlag != null) {
    			partyForm.tMemberSendList = tMemberService.findAllOrderById(containsOB);
    			return "partyConfirm.jsp";
    		}
    		
    		//メールを送る人のリスト
    		HashSet<TMember> sendTMemberSet = new HashSet<TMember>();
    		//部で選択されていた場合は部ごとに会員を検索してsetに格納する
    		if (partyForm.clubListCheck != null) {
    			for (String cLubId : partyForm.clubListCheck) {
    				List<TMemberClub> tMemberClubList = tMemberClubService.findByClubId(cLubId, containsOB);
    				sendTMemberSet.addAll(getTMmeberByTMemberClubList(tMemberClubList));
    			}
    		}
    		
			partyForm.tMemberSendList = new ArrayList<TMember>(sendTMemberSet);
    	}
    		return "partyConfirm.jsp";
	}
    
	/**
	 * TMemberClubリストからTMemberのリストを取得
	 * @param tMemberClubList
	 * @return
	 */
	protected ArrayList<TMember> getTMmeberByTMemberClubList(List<TMemberClub> tMemberClubList) {
		ArrayList<TMember> rtnList = new ArrayList<TMember>();
		for (TMemberClub tMemberClub : tMemberClubList) {
			rtnList.add(tMemberClub.tMember);
		}
		return rtnList;
	}

	@Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	/** 入力された情報をエンティティにコピー　**/
        	TParty party = new TParty();
        	//例外として.excludes()内に書いてある要素は省く(コピーしない)。日時関係はyyyy/mm/dd hh:mm:ssの形にしてTimestamp型に変化する必要がある。
        	Beans.copy(partyForm, party).excludes("meetingDay","meetingTime","meetingDeadlineDay").execute();
        	
        	//編集者のIDを入れる
        	party.creatorId = loginAdminDto.memberId;
        	
        	//日付と日時をString型からDate型に変換
        	try {
        		party.meetingDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDay);
        		party.meetingTime = new SimpleDateFormat("HH:mm").parse(partyForm.meetingTime);
        		party.meetingDeadlineDay = new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDeadlineDay);
			} catch (ParseException e) {
				//起こりえない
				e.printStackTrace();
			}
        	party.deleteFlag = Boolean.valueOf(false);
        	//DBに追加
        	tPartyService.insert(party);
        	
        	//完了画面から詳細画面遷移のためにIDを取得
        	partyForm.id = party.id;
        	
        	//obを含めるかどうか
        	boolean containsOb = partyForm.ObAttendFlag;
        	
        	Collection<TMember> tMemberList;
        	if (partyForm.attendClub != null) {
        		tMemberList = new HashSet<TMember>();
        		
        		//PartyClubテーブルにレコードを挿入する
        		insertTPartyClub(party.id);
        		//部ごとに会員をセットする
        		for (String cLubId : partyForm.clubListCheck) {
    				List<TMemberClub> tMemberClubList = tMemberClubService.findByClubId(cLubId, containsOb);
    				tMemberList.addAll(getTMmeberByTMemberClubList(tMemberClubList));
    			}
        	} else {
        		tMemberList = tMemberService.findAllOrderById(containsOb);
        	}
        	//PartyAttendテーブルに未回答として対象者を登録する
        	insertPartyAttend(tMemberList, party.id);
        	
        	tParty = new TParty();
        	tParty = party;
        	
	        //メールの送信がない場合はそのまま完了画面に遷移
	        if (!partyForm.mailSendFlag) {
	        	return "partyComplete.jsp";	
	        }else {
				return sendMail();
			}
        } else {
        	return "/common/error.jsp";
        }
	}

	/**
	 * PartyAttendに未回答として登録する
	 * @param tMemberList
	 * @param partyId
	 */
	protected void insertPartyAttend(Collection<TMember> tMemberList, Integer partyId) {
		for (TMember tMemberOne : tMemberList) {
    		TPartyAttend tPartyAttend = new TPartyAttend();
    		tPartyAttend.memberId = tMemberOne.id;
    		tPartyAttend.partyId = partyId;
    		tPartyAttend.attend = Integer.valueOf(PartyAttendCode.UNSUBMITTED.getCode());
    		tPartyAttendService.insert(tPartyAttend);
    	}
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

	//以下メールの配信処理
    @Execute(validator = false)
	public String sendMail() {
    	
    	//メールの送信者のID
    	partyForm.registMemberId = loginAdminDto.memberId;
    	
    	//TMailにメールの内容を追加する
    	TMail tMail = new TMail();
    	Beans.copy(partyForm, tMail).execute();
   
    	//メールを送信する
    	MailManager manager = new MailManager();
    	manager.setTitle(partyForm.title);
    	manager.setContent(partyForm.content);
    	manager.setToAddress(partyForm.tMemberSendList.toArray(new TMember[0]));
    	if (manager.sendMail()) {
    		mailMsg = "メールを正常に送信しました。";
    		tMail.errorFlag = false;
    	} else {
    		mailMsg = "メールの送信に失敗しました。";
    		tMail.errorFlag = true;
    	}   
    	
    	//メールの内容を登録する
    	tMailService.insert(tMail);
    	
    	//TMailSendAttendにメールの送信相手を追加する
    	TMailSendMember tMailSendMember = new TMailSendMember();
    	for (TMember tMemberOne : partyForm.tMemberSendList) {
    		tMailSendMember.mailId = tMail.id;
    		tMailSendMember.memberId = tMemberOne.id;
    		tMailSendMemberService.insert(tMailSendMember);
    	}
    	
    	//会議とメールを紐付けるDBに登録する
    	TPartySendMail tPartySendMail = new TPartySendMail();
    	tPartySendMail.PartyId = tParty.id;
    	tPartySendMail.MailId = tMail.id;
    	tPartySendMailService.insert(tPartySendMail);
    	
    return "partyComplete.jsp";	
    }
}
