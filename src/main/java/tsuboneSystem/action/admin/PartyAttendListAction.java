package tsuboneSystem.action.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.dto.ExcelDto;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.entity.TPartyClub;
import tsuboneSystem.form.PartyAttendForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;
import tsuboneSystem.fpao.ExcelFpao;

public class PartyAttendListAction {

	/** アクションネーム */
	public String actionName = "PartyAttend";
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	/** 会議の一覧 */
	public List<TParty> partyItem;
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyAttendForm partyAttendForm;
	
	/** partyDto */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** excelファイル出力 */
	@Resource
	protected ExcelFpao excelFpao;
	
	@Resource
    protected HttpServletResponse httpServletResponse;
	
	/** エクセル出力のためのDto */
	@Resource
	protected ExcelDto excelDto;
	
	/** ログインユーザ*/
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	public boolean deadFlag;
	
	/** 一覧画面表示　*/
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}")
    @RemoveSession(name = "partyAttendForm")
	public String index() {
    	
    	TParty party = tPartyService.findById(partyAttendForm.id);
    	Beans.copy(party, partyDto).execute();

    	//現在時刻を取得し、期限内か判断する
    	Date dateNow = new Date();
    	deadFlag = partyDto.deadFlag(party, dateNow);
	    	
    	//全員のリストを取得し、以下の処理の中で該当のメンバーは削除してき、残ったメンバーが出欠席を返さないクズ。
    	if (party.ObAttendFlag){
    		//OBの出席を含む
    		if (party.tPartyClubList.size() > 0) {
    			//部によって出席対象が絞られている場合
    			List<TPartyClub> tPartyClub = tPartyClubService.findByPartyId(party.id);
    			Set<TMember> tMemberSet = new HashSet<TMember>();
    			for (TPartyClub tPartyClubOne : tPartyClub) {
    				for (TMemberClub tMemberClubOne : tPartyClubOne.tClub.tMemberClubList) {
    					tMemberSet.add(tMemberClubOne.tMember);
        				}
    			}
    			partyAttendForm.tMemberKuzu = new ArrayList<TMember>();
    			for (TMember tMember : tMemberSet) {
    				partyAttendForm.tMemberKuzu.add(tMember);
    			}
    		}else{
    			//全員が出席対象
    			partyAttendForm.tMemberKuzu = tMemberService.findByIdAll();
    		}	
    	}else{
    		//OBの出席を含まない
    		if (party.tPartyClubList.size() > 0) {
    			//部によって出席対象が絞られている場合
    			List<TPartyClub> tPartyClub = tPartyClubService.findByPartyIdNoOB(party.id);
    			Set<TMember> tMemberSet = new HashSet<TMember>();
    			for (TPartyClub tPartyClubOne : tPartyClub) {
    				for (TMemberClub tMemberClubOne : tPartyClubOne.tClub.tMemberClubList) {
    					tMemberSet.add(tMemberClubOne.tMember);
        				}
    			}
    			partyAttendForm.tMemberKuzu = new ArrayList<TMember>();
    			for (TMember tMember : tMemberSet) {
    				partyAttendForm.tMemberKuzu.add(tMember);
    			}
    		}else{
    			//全員が出席対象
    			partyAttendForm.tMemberKuzu = tMemberService.findByIdNoOBAll();
    		}	
    	}
    	
    	partyAttendForm.mapKuzuSS = new HashMap<String,String>();
    	partyDto.mapKuzuSS = new HashMap<String,String>();
    	for (TMember memberKuzuOne : partyAttendForm.tMemberKuzu) {
    		partyAttendForm.mapKuzuSS.put(memberKuzuOne.id.toString(), memberKuzuOne.hname);
    		partyDto.mapKuzuSS.put(memberKuzuOne.id.toString(), memberKuzuOne.hname);
    	}
    	
    	//出席している人のリスト
    	partyAttendForm.tAttendOn = tPartyAttendService.findByPartyIdAndAttendOn(partyAttendForm.id);
    	for (TPartyAttend attendOn : partyAttendForm.tAttendOn){
    		partyAttendForm.tMemberOn.add(tMemberService.findById(attendOn.memberId));
    		partyAttendForm.mapKuzuSS.remove(attendOn.memberId.toString());
    		partyDto.mapKuzuSS.remove(attendOn.memberId.toString());
    	}
    	
    	//欠席している人のリスト
    	partyAttendForm.tAttendOn = tPartyAttendService.findByPartyIdAndAttendOff(partyAttendForm.id);
    	for (TPartyAttend attendOff : partyAttendForm.tAttendOn){
    		partyAttendForm.tMemberOff.add(tMemberService.findById(attendOff.memberId));
    		partyAttendForm.mapKuzuSS.remove(attendOff.memberId.toString());
    		partyDto.mapKuzuSS.remove(attendOff.memberId.toString());
    	}
    	
    	//partyDtoの出欠席リストにもコピーしておく(更新や削除のActionなどで使用する。)
    	partyDto.tMemberOn = partyAttendForm.tMemberOn;
    	partyDto.tMemberOff = partyAttendForm.tMemberOff;
    	
        return "partyAttendList.jsp";
	}
    
    /**　エクセル出力　*/
    @Execute(validator = false)
    public String createExcel(){
    	
    	ExcelDto dto = new ExcelDto();
    	dto.setMeetingName(partyDto.meetingName);
    	dto.setMemberList(partyDto.tMemberOn);
    	
    	HSSFWorkbook wb = excelFpao.excelTemplate(dto);
    	
    	httpServletResponse.setHeader(
    			"Content-Disposition",
    			"attachment; filename=" + System.currentTimeMillis() + ".xls");
    	
    	try {
    		OutputStream out = httpServletResponse.getOutputStream();
    		wb.write(out);
    	        out.close();		
    	} catch (IOException e) {} 	
    	
    return null;	
    }
    
    
    
    /** メール配信　*/
    @Execute(validator = false)
    public String contentRegist(){
    	
    	// 2重送信防止のためのTokenの生成
        TokenProcessor.getInstance().saveToken(request);
        
    return "partyMailRegist.jsp";	
    }
    
    @Execute(validator = false)
    public String confirm(){
    	
    	//出席する人にメールを送る
    	partyAttendForm.tMemberSendList = partyDto.tMemberOn;
    	
    return "partyMailConfirm.jsp";	
    }
    
    @Execute(validator = false)
    public String complete(){
	
    	// 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールの送信者のID
        	partyAttendForm.registMemberId = loginAdminDto.memberId;
        	
        	//TMailにメールの内容を追加する
        	TMail tMail = new TMail();
        	Beans.copy(partyAttendForm, tMail).execute();
        	tMailService.insert(tMail);
        	
        	//TMailSendAttendにメールの送信相手を追加する
        	for (TMember tMemberOne : partyAttendForm.tMemberSendList) {
        		TMailSendMember tMailSendMember = new TMailSendMember();
        		tMailSendMember.mailId = tMail.id;
        		tMailSendMember.memberId = tMemberOne.id;
        		tMailSendMemberService.insert(tMailSendMember);
        	}
        	
        	//メールを送信する
        	MailManager manager = new MailManager();
        	manager.setTitle(partyAttendForm.title);
        	manager.setContent(partyAttendForm.content);
        	manager.setToAddress(partyAttendForm.tMemberSendList.toArray(new TMember[0]));
        	if (manager.sendMail()) {
        		mailMsg = "メールを正常に送信しました。";
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        	}
        	
        }
        
    return "partyMailComplete.jsp";	
    }   
}
