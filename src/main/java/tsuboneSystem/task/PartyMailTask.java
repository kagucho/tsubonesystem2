package tsuboneSystem.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyAttendService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

/*
 * 会議締め切り確認時の自動メール送信
 * 起動時間は毎日午後18時
 * 
 * 
 * 必須にされている会議は5日前に一回、3日前から一日一通
 * 必須にされていない会議は3日前に一回、当日に一回
 * 
 * @CronTrigger(expression = "0 44 17 * * ?")：17時44分00秒にdoExecute()メソッドを実行するよってこと
 */


@Task
@CronTrigger(expression = "0 00 18 * * ?")
public class PartyMailTask extends AbstractTask{
	
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
	
	/** TPartyServiceのサービスクラス */
	@Resource
	protected TPartyService tPartyService;
	
	/** TPartyAttendServiceのサービスクラス */
	@Resource
	protected TPartyAttendService tPartyAttendService;
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;
	
	/** 出席対象者 */
	public List<TMember> tSendMember = new ArrayList<TMember>();
	
	/** 送信エラーフラグ */
	public boolean errorFlag;
	
	/** 5日前 */
	public final static int FIVE_DAY = 5;
	
	/** 3日前 */
	public final static int TREE_DAY = 3;
	
	/** 2日前 */
	public final static int TWO_DAY = 2;
	
	/** 1日前 */
	public final static int ONE_DAY = 1;
	
	/** 当日 */
	public final static int TODAY = 0;
	
    public void sendMail(List<TParty> tPartyList){
    	if (tPartyList.size() > 0) {
    		//締め切られていいない会議が存在したら以下を実行
    		for (TParty tPartyOne : tPartyList) {
    			//該当の会議で、まだ出欠席を出していないメンバー一覧を取得
    			List<TPartyAttend> tPartyAttends = tPartyAttendService.findByPartyId_UNSUBMITTED(tPartyOne.id);
    			
    	    	for (TPartyAttend tPartyAttendOne : tPartyAttends) {
    	    		//メンバー情報を取り出し
    	    		tSendMember.add(tPartyAttendOne.tMember);
    	    	}
    	    	
    	    	//タイトルを作る
    	    	StringBuilder sb = new StringBuilder();
    	    	sb.append("(出欠席)　");
    	    	sb.append(tPartyOne.meetingName);
    	    	String title = new String(sb);
    	    	
    	    	//内容を作る
    	    	StringBuilder sbc = new StringBuilder();
    	    	sbc.append("会議名:　");
    	    	sbc.append(tPartyOne.meetingName);
    	    	sbc.append("\n");
    	    	sbc.append("会議内容: ");
    	    	sbc.append(tPartyOne.meetingMemo);
    	    	sbc.append("\n");
    	    	sbc.append("開催日: ");
    	    	sbc.append(tPartyOne.meetingDay);
    	    	sbc.append("\n");
    	    	sbc.append("開催時間: ");
    	    	sbc.append(tPartyOne.meetingTime);
    	    	sbc.append("\n");
    	    	sbc.append("締め切り日時: ");
    	    	sbc.append(tPartyOne.meetingDeadlineDay);
    	    	sbc.append("\n");
    	    	sbc.append("\n");
    	    	sbc.append("以上の会議にまだ出欠を出していません。とっとと出欠を出しましょう。");
    	    	sbc.append("\n");
    	    	sbc.append("\n");
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
            	tMailService.insert(tMail);
            	
            	for (TMember tMemberOne : tSendMember) {
            		TMailSendMember tMailSendMember = new TMailSendMember();
            		tMailSendMember.mailId = tMail.id;
            		tMailSendMember.memberId = tMemberOne.id;
            		tMailSendMemberService.insert(tMailSendMember);
            	}
    		}
    	}
    }

	@Override
	String getTascName() {
		return "未提出者メール配信";
	}

	@Override
	void process() throws Exception {
		//実行された時点で、締め切られていない会議の一覧を取得
    	Date dateNow = new Date();
    	
    	//出欠必須であり締め切り日の5日前の会議一覧
    	List<TParty>  tPartyListHISSU_FIVE = tPartyService.findBy_Deadline_PULS(dateNow, FIVE_DAY, true);
    	sendMail(tPartyListHISSU_FIVE);
    	
    	//出欠必須であり締め切り日の3日前から締め切り日までに存在する会議一覧
    	List<TParty>  tPartyListHISSU_TREE = tPartyService.findBy_Deadline_PULS(dateNow, TREE_DAY, true);
    	sendMail(tPartyListHISSU_TREE);
    	
    	//出欠必須であり締め切り日の2日前から締め切り日までに存在する会議一覧
    	List<TParty>  tPartyListHISSU_TWO_TREE = tPartyService.findBy_Deadline_PULS(dateNow, TWO_DAY, true);
    	sendMail(tPartyListHISSU_TWO_TREE);
    		
    	//出欠必須であり締め切り日の1日前から締め切り日までに存在する会議一覧
    	List<TParty>  tPartyListHISSU_ONE_TREE = tPartyService.findBy_Deadline_PULS(dateNow, ONE_DAY, true);
    	sendMail(tPartyListHISSU_ONE_TREE);
    	
    	//出欠必須であり締め切り日当日の会議一覧
    	List<TParty>  tPartyListHISSU_TODAY = tPartyService.findBy_Deadline_PULS(dateNow, TODAY, true);
    	sendMail(tPartyListHISSU_TODAY);
    	
    	//締め切り日の3日前の会議一覧
    	List<TParty>  tPartyListTREE = tPartyService.findBy_Deadline_PULS(dateNow, TREE_DAY, false);
    	sendMail(tPartyListTREE);
    	
    	//締め切り日の1日前の会議一覧
    	List<TParty>  tPartyListTODAY = tPartyService.findBy_Deadline_PULS(dateNow, TODAY, false);
    	sendMail(tPartyListTODAY);
	}
    
}
