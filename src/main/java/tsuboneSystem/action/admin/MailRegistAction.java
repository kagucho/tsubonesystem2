package tsuboneSystem.action.admin;

import java.util.ArrayList;
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
import tsuboneSystem.form.MailForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MailRegistAction {
	
	/** アクションネーム */
	public String actionName = "MailRegist";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** MailFormのアクションフォーム */
	@ActionForm
	@Resource
	protected MailForm mailForm;

	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** 入力画面(送信先選択) */
    @Execute(validator = false, reset = "resetInput")
	public String index() {
    	
    	/** 2重送信防止のためのTokenの生成　**/
        TokenProcessor.getInstance().saveToken(request);
        
        /* メンバーの一覧を取得する　*/
        mailForm.tMemberItem = tMemberService.findAllOrderById();
        
      //登録されている部をすべてリストの形で呼び出す
        mailForm.clubList = tClubService.findAllOrderById();
        
        //マップを作る。形はkey(数値)とvalu(名称)の２個セットの形
        mailForm.clubMapSS = new HashMap<String,String>();
        
        //for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub club : mailForm.clubList) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	mailForm.clubMapSS.put(club.id.toString(), club.ClubName);
        }
 
        return viewinput();
	}
    
  //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "index.jsp";
    }
    
    /** 確認画面 */
    @Execute(validator = true, input = "index.jsp", validate="validateBase", stopOnValidationError = false, reset = "resetInput")
	public String confirm() {
    	
    	//OBを除いた全員
		if (mailForm.mailSendAllFlag != null && mailForm.mailSendOBFlag == null) {
			mailForm.tMemberSendList = tMemberService.findByIdNoOBAll();	
		//OBを含めた全員
		}else if (mailForm.mailSendAllFlag != null && mailForm.mailSendOBFlag != null) {
			mailForm.tMemberSendList = tMemberService.findAllOrderById();	
		//部で指定されていた場合
		}else if (mailForm.clubListCheck != null) {
			mailForm.MemberSendSet = new HashSet<Integer>();
			//選択された部をひとつ取り、それらに紐づくメンバーIDをsetに入れる
			for (String cLubIDOne : mailForm.clubListCheck) {
				List<TMemberClub> tMemberClubList = new ArrayList<TMemberClub>();
				tMemberClubList = tMemberClubService.findByClubId(cLubIDOne);
				for (TMemberClub tMemberClubOne : tMemberClubList) {
					if (mailForm.mailSendOBFlag == null) {
						if (!tMemberClubOne.tMember.obFlag) {
    						mailForm.MemberSendSet.add(tMemberClubOne.MemberId);
    					}	
					}else{
						mailForm.MemberSendSet.add(tMemberClubOne.MemberId);
					}
				}
			}
			mailForm.tMemberSendList = new ArrayList<TMember>();
			for (Integer one : mailForm.MemberSendSet) {
				mailForm.tMemberSendList.add(tMemberService.findById(one));
			}
		}	
		return "mailConfirm.jsp";
	}
    
    /** 完了画面 */
    @Execute(validator = false)
	public String complete() {
    	
    	/** 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	mailForm.registMemberId = loginAdminDto.memberId;
        	
        	//TMailにメールの内容を追加する
        	TMail tMail = new TMail();
        	Beans.copy(mailForm, tMail).execute();

        	//メールを送信する
        	MailManager manager = new MailManager();
        	manager.setTitle(mailForm.title);
        	manager.setContent(mailForm.content);
        	manager.setToAddress(mailForm.tMemberSendList.toArray(new TMember[0]));
        	if (manager.sendMail()) {
        		mailMsg = "メールを正常に送信しました。";
        		tMail.errorFlag = false;
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        		tMail.errorFlag = true;
        	}
        	
        	tMailService.insert(tMail);
        	
        	//TMailSendAttendにメールの送信相手を追加する
        	for (TMember tMemberOne : mailForm.tMemberSendList) {
        		TMailSendMember tMailSendMember = new TMailSendMember();
        		tMailSendMember.mailId = tMail.id;
        		tMailSendMember.memberId = tMemberOne.id;
        		tMailSendMemberService.insert(tMailSendMember);
        	}
        	
        	
        }
        return "mailComplete.jsp";
	}
}
