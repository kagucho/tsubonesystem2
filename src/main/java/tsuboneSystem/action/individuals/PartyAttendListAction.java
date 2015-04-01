package tsuboneSystem.action.individuals;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.ExcelDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.PartyAttendForm;
import tsuboneSystem.fpao.ExcelFpao;
import tsuboneSystem.original.util.MailManagerUtil;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

public class PartyAttendListAction {
	
	public String actionName = "PartyAttend";
	
	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyAttendForm partyAttendForm;
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** partyDto */
	@Resource
	public PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** HttpServlet */
	@Resource
    protected HttpServletResponse httpServletResponse;
	
	@Resource
    protected HttpServletRequest httpServletRequest;
	
	/** excelファイル出力 */
	@Resource
	protected ExcelFpao excelFpao;
	
	/** 会議の一覧 */
	public List<TParty> partyItem;
	
	/** メール送信可否 */
	public String mailMsg = null;
	
	public boolean deadFlag;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{id}")
    @RemoveSession(name = "partyAttendForm")
	public String index() {
    	

    	TParty party = tPartyService.findById(partyAttendForm.id);
    	Beans.copy(party, partyDto).excludes("myPartyFlag").execute();
    	
    	//ログイン者が会議の制作者かどうか
    	if(party.creatorId.equals(loginMemberDto.memberId)){
    		partyDto.myPartyFlag = true;
    	}else{
    		partyDto.myPartyFlag = false;
    	}

    	//現在時刻を取得し、期限内か判断する
    	Date dateNow = new Date();
    	deadFlag = partyDto.deadFlag(party, dateNow);
	    	
    	//出欠席を返さないクズ。
    	partyAttendForm.tAttendKuzu = tPartyAttendService.findByPartyId_UNSUBMITTED(partyAttendForm.id);
    	partyAttendForm.tMemberKuzu = new ArrayList<TMember>();
    	for (TPartyAttend tPartyAttendOne : partyAttendForm.tAttendKuzu) {
    		partyAttendForm.tMemberKuzu.add(tPartyAttendOne.tMember);
    	}
    	
    	//出席している人のリスト
    	partyAttendForm.tAttendOn = tPartyAttendService.findByPartyIdAndAttendOn(partyAttendForm.id);
    	partyAttendForm.tMemberOn = new ArrayList<TMember>();
    	for (TPartyAttend tPartyAttendOne : partyAttendForm.tAttendOn) {
    		partyAttendForm.tMemberOn.add(tPartyAttendOne.tMember);
    	}
    	
    	//欠席している人のリスト
    	partyAttendForm.tAttendOff = tPartyAttendService.findByPartyIdAndAttendOff(partyAttendForm.id);
    	partyAttendForm.tMemberOff = new ArrayList<TMember>();
    	for (TPartyAttend tPartyAttendOne : partyAttendForm.tAttendOff) {
    		partyAttendForm.tMemberOff.add(tPartyAttendOne.tMember);
    	}
    	
    	//partyDtoの出欠席リストにもコピーしておく(更新や削除のActionなどで使用する。)
    	partyDto.tMemberOn = partyAttendForm.tMemberOn;
    	partyDto.tMemberOff = partyAttendForm.tMemberOff;
    	partyDto.tMemberKuzu = partyAttendForm.tMemberKuzu;
    	
        return "partyAttendList.jsp";
	}
    
    /**　エクセル出力　
     * @throws UnsupportedEncodingException */
    @Execute(validator = false)
    public String createExcel() throws UnsupportedEncodingException{
    	
    	ExcelDto dto = new ExcelDto();	
    	dto.setMeetingName(partyDto.meetingName);
    	dto.setMemberList(partyDto.tMemberOn);
    	
    	HSSFWorkbook wb = excelFpao.excelTemplate(dto);
    	
		String fileName = partyDto.meetingName + "_の出席者一覧" + ".xls";
    	
    	if (httpServletRequest.getHeader("User-Agent").indexOf("MSIE") == -1) {
			// Firefox, Opera 11
    		httpServletResponse.setHeader("Content-Disposition", String.format(Locale.JAPAN, "attachment; filename*=utf-8'jp'%s", URLEncoder.encode(fileName, "utf-8")));
		} else {
			// IE7, 8, 9
			httpServletResponse.setHeader("Content-Disposition", String.format(Locale.JAPAN, "attachment; filename=\"%s\"", new String(fileName.getBytes("MS932"), "ISO8859_1")));
		}
    	
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
    public String confirmMail(){
    	
    	//出席する人にメールを送る
    	partyAttendForm.tMemberSendList = partyDto.tMemberOn;
    	
    return "partyMailConfirm.jsp";	
    }
    
    @Execute(validator = false)
    public String complete(){
	
    	// 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//メールを送信する
        	MailManagerUtil mailUtil = new MailManagerUtil();
        	mailUtil.setRegistId(loginMemberDto.memberId);
        	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
        	mailUtil.setTitle(partyAttendForm.title);
        	mailUtil.setContent(partyAttendForm.content);	
        	mailUtil.setLinkUrlFlag(false);
        	mailUtil.setToAddressActorSplit(partyAttendForm.tMemberSendList);
        	mailUtil.sendMail();
        	if (!mailUtil.getSendMailResult()) {
        		mailMsg = "メールを正常に送信しました。";
        	} else {
        		mailMsg = "メールの送信に失敗しました。";
        	}
        }
    return "partyMailComplete.jsp";	
    }
    
}
