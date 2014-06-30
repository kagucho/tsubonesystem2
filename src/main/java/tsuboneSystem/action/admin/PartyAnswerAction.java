package tsuboneSystem.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.TokenProcessor;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.PartyAnswerCode;
import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.PartyDto;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TPartyAnswer;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.form.PartyQuestionAnswerForm;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TPartyAnswerService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyQuestionService;
import tsuboneSystem.service.TPartyService;

public class PartyAnswerAction {
	
	public String actionName = "PartyDetail";
	
	/** PartyDtoのサービスクラス */
	@Resource
	protected PartyDto partyDto;
	
	/** TPartyのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TClubServiceのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService  tPartyClubService;
	
	/** TPartyAnswerServiceのサービスクラス */
	@Resource
	protected TPartyAnswerService  tPartyAnswerService;
	
	/** TPartyQuestionServiceのサービスクラス */
	@Resource
	protected TPartyQuestionService  tPartyQuestionService;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** PartyQuestionAnswerFormのアクションフォーム */
	@ActionForm
	@Resource
	protected PartyQuestionAnswerForm partyQuestionAnswerForm;
	
	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;
	
	/** LoginAdminDto */
	@Resource
	protected LoginAdminDto loginAdminDto;
	
	/** 送信エラーフラグ */
	public boolean errorFlag;
	
    @SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "{partyId}/{questionId}", reset = "resetInput")
	public String index() {
    	
    	partyQuestionAnswerForm.tPartyQuestion = tPartyQuestionService.findById(partyQuestionAnswerForm.questionId);
    	
    	//メール送信対象のマップ
    	partyQuestionAnswerForm.answerSendKindMap = new HashMap<String, String>();
        for (Integer i=0; i<=2; i++) {
        	partyQuestionAnswerForm.answerSendKindMap.put(i.toString(), PartyAnswerCode.getnameByCode(i.toString()));
        }
    	
        return viewinput();
	}
    
  //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "partyAnswerInput.jsp";
    }
    
    //質問確認画面
    @Execute(validator = true, input = "partyAnswerInput.jsp")
    public String answerConfirm(){
    	
    	return "partyAnswerConfirm.jsp";
    }
    
    //質問完了画面
    @SuppressWarnings("boxing")
	@Execute(validator = false)
    public String answerComplete(){
    	
    	/** 2重送信防止のためTokenが正常な場合にのみ レコード追加処理を行う	　**/
        if (TokenProcessor.getInstance().isTokenValid(request, true)) {
        	
        	//質問内容を登録する
        	TPartyAnswer tPartyAnswer = new TPartyAnswer();
        	tPartyAnswer.questionId = partyQuestionAnswerForm.questionId;
        	tPartyAnswer.memberId = loginAdminDto.memberId;
        	tPartyAnswer.answer = partyQuestionAnswerForm.answer;
        	tPartyAnswer.answerSendKind = partyQuestionAnswerForm.answerSendKind;
        	tPartyAnswerService.insert(tPartyAnswer);
        	
        	//会議の登録者にメールを送る
        	if(partyQuestionAnswerForm.answerSendKind != Integer.parseInt(PartyAnswerCode.NO_SEND.getCode())){
        		
        		List<TMember> tSendMember = new ArrayList<TMember>();
        		
        		//送信対象
        		if (partyQuestionAnswerForm.answerSendKind == Integer.parseInt(PartyAnswerCode.QUESTIONER.getCode())) {
        			//質問者のみ
            		tSendMember.add(partyQuestionAnswerForm.tPartyQuestion.tMember);
        		}else if (partyQuestionAnswerForm.answerSendKind == Integer.parseInt(PartyAnswerCode.ALL.getCode())) {
        			//質問者と会議の出席者全員
        			for(TPartyAttend one : partyQuestionAnswerForm.tPartyQuestion.tParty.tPartyAttendList){
        				tSendMember.add(one.tMember);
        			}
        			if(tPartyAttendService.findByMemberIdWithPartyId(partyQuestionAnswerForm.tPartyQuestion.memberId.toString(), partyQuestionAnswerForm.partyId) == null){
        				//質問者が出席対象者以外だった場合
        				tSendMember.add(partyQuestionAnswerForm.tPartyQuestion.tMember);
        			}
        		}
        		   		
        		//タイトルを作る
    	    	StringBuilder sb = new StringBuilder();
    	    	sb.append("[");
    	    	sb.append(partyQuestionAnswerForm.tPartyQuestion.tParty.meetingName);
    	    	sb.append("]");
    	    	sb.append("に対して解答を受け付けました");
    	    	String title = new String(sb);
    	    	
    	    	//内容を作る
    	    	StringBuilder sbc = new StringBuilder();
    	    	sbc.append("会議名:　");
    	    	sbc.append(partyQuestionAnswerForm.tPartyQuestion.tParty.meetingName);
    	    	sbc.append("\n");
    	    	sbc.append("質問内容:　");
    	    	sbc.append("\n");
    	    	sbc.append(partyQuestionAnswerForm.tPartyQuestion.question);
    	    	sbc.append("\n");
    	    	sbc.append("\n");
    	    	sbc.append("回答者:　");
    	    	sbc.append(loginAdminDto.tMemberLogin.hname);
    	    	sbc.append("\n");
    	    	sbc.append("回答内容:　");
    	    	sbc.append("\n");
    	    	sbc.append(partyQuestionAnswerForm.answer);
    	    	String content = new String(sbc);
    	    	
    	    	//メールを送信する
            	MailManager manager = new MailManager();
            	manager.setTitle(title);
            	manager.setContent(content);
            	manager.setToAddress(tSendMember.toArray(new TMember[0]));
            	if (manager.sendMail()){
            		errorFlag = false;
            	}else{
            		errorFlag = true;
            	}
            	
            	//以下メールの送信履歴を残す
            	TMail tMail = new TMail();
            	tMail.title = title;
            	tMail.content = content;
            	tMail.errorFlag = errorFlag;
            	tMail.registMemberId = loginAdminDto.memberId;
            	tMailService.insert(tMail);
            	
            	for (TMember tMemberOne : tSendMember) {
            		TMailSendMember tMailSendMember = new TMailSendMember();
            		tMailSendMember.mailId = tMail.id;
            		tMailSendMember.memberId = tMemberOne.id;
            		tMailSendMemberService.insert(tMailSendMember);
            	}	
        	}
        }
    	return "partyAnswerComplete.jsp";
    }
}
