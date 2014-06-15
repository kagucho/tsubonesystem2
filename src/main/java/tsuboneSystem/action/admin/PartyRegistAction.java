
package tsuboneSystem.action.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.form.PartyForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyClubService;
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
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	@Execute(validator = false, reset = "resetInput")
	public String index() {
		
		/** 2重登録防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        /** 詳細画面にて部の表示のためにmapを作成する　**/
        //登録されている部をすべてリストの形で呼び出す
        partyForm.clubList = tClubService.findAllOrderById();
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        partyForm.clubMapSS = new HashMap<String,String>();
        
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : partyForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	partyForm.clubMapSS.put(club.id.toString(), club.ClubName);
        }
         
        return "partyInput.jsp";
	}
    
	@Execute(validator = true, input = "partyInput.jsp", validate="validateBase", stopOnValidationError = false) 
	public String confirm() {
    	
    	if (partyForm.mailSendFlag) {
    		
    		//OBを除いた全員
    		if (partyForm.mailSendAllFlag != null && partyForm.mailSendOBFlag == null) {
    			partyForm.tMemberSendList = tMemberService.findByIdNoOBAll();	
    		//OBを含めた全員
    		}else if (partyForm.mailSendAllFlag != null && partyForm.mailSendOBFlag != null) {
    			partyForm.tMemberSendList = tMemberService.findAllOrderById();	
    		//部で指定されていた場合
    		}else if (partyForm.clubListCheck != null) {
    			partyForm.MemberSendSet = new HashSet<Integer>();
    			//選択された部をひとつ取り、それらに紐づくメンバーIDをsetに入れる
    			for (String cLubIDOne : partyForm.clubListCheck) {
    				List<TMemberClub> tMemberClubList = new ArrayList<TMemberClub>();
    				tMemberClubList = tMemberClubService.findByClubId(cLubIDOne);
    				for (TMemberClub tMemberClubOne : tMemberClubList) {
    					if (partyForm.mailSendOBFlag == null) {
    						if (!tMemberClubOne.tMember.obFlag) {
        						partyForm.MemberSendSet.add(tMemberClubOne.MemberId);
        					}	
    					}else{
    						partyForm.MemberSendSet.add(tMemberClubOne.MemberId);
    					}
    				}
    			}
    			partyForm.tMemberSendList = new ArrayList<TMember>();
    			for (Integer one : partyForm.MemberSendSet) {
    				partyForm.tMemberSendList.add(tMemberService.findById(one));
    			}
    		}	
    		return "partyConfirm.jsp";
    	}else{
    		return "partyConfirm.jsp";
    	}   
	}
    
	@Execute(validator = false)
	public String complete() {
    	
    	/** 2重登録防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	/** 入力された情報をエンティティにコピー　**/
        	TParty party = new TParty();
        	//例外として.excludes()内に書いてある要素は省く(コピーしない)。日時関係はyyyy/mm/dd hh:mm:ssの形にしてTimestamp型に変化する必要がある。
        	Beans.copy(partyForm, party).excludes("meetingDay","meetingTime","meetingDeadlineDay","meetingDeadlineTime").execute();
        	
        	//編集者のIDを入れる
        	party.creatorId = loginAdminDto.memberId;
        	
        	//日付と日時をString型からDate型に変換
        	try {
				Date meetingDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDay.toString()).getTime());
				Date meetingTime = new Date(new SimpleDateFormat("HH:mm").parse(partyForm.meetingTime.toString()).getTime());
				Date meetingDeadlineDay = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(partyForm.meetingDeadlineDay.toString()).getTime());
				Date meetingDeadlineTime = new Date(new SimpleDateFormat("HH:mm").parse(partyForm.meetingDeadlineTime.toString()).getTime());
				party.meetingDay = meetingDay;
				party.meetingTime = meetingTime;
				party.meetingDeadlineDay = meetingDeadlineDay;
				party.meetingDeadlineTime = meetingDeadlineTime;
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	
        	party.deleteFlag = Boolean.valueOf(false);
        	
        	//DBに追加
        	tPartyService.insert(party);
        	
        	if (partyForm.attendClub != null) {
        		//出席対象者に指定があった場合
        		for (String clubId : partyForm.attendClub) {
        			//partyIdとclubIdをセットで格納する
        			TPartyClub tPartyClub = new TPartyClub();
        			tPartyClub.PartyId = party.id;
        			tPartyClub.ClubId = Integer.valueOf(clubId);
        			tPartyClubService.insert(tPartyClub);
        		}
        	}
        	
        }
        //メールの送信がない場合はそのまま完了画面に遷移
        if (!partyForm.mailSendFlag) {
        	return "partyComplete.jsp";	
        }else {
        	return sendMail();
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
    	tMailService.insert(tMail);
    	
    	//TMailSendAttendにメールの送信相手を追加する
    	for (TMember tMemberOne : partyForm.tMemberSendList) {
    		TMailSendMember tMailSendMember = new TMailSendMember();
    		tMailSendMember.mailId = tMail.id;
    		tMailSendMember.memberId = tMemberOne.id;
    		tMailSendMemberService.insert(tMailSendMember);
    	}
   
    	//メールを送信する
    	MailManager manager = new MailManager();
    	manager.setTitle(partyForm.title);
    	manager.setContent(partyForm.content);
    	manager.setToAddress(partyForm.tMemberSendList.toArray(new TMember[0]));
    	if (manager.sendMail()) {
    		mailMsg = "メールを正常に送信しました。";
    	} else {
    		mailMsg = "メールの送信に失敗しました。";
    	}   	
    return "partyComplete.jsp";	
    }
}
