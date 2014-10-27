package tsuboneSystem.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.original.util.MailManagerUtil;
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
@CronTrigger(expression = "0 57 20 * * ?")
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
    			
    			tSendMember = new ArrayList<TMember>();
    	    	for (TPartyAttend tPartyAttendOne : tPartyAttends) {
    	    		//メンバー情報を取り出し
    	    		tSendMember.add(tPartyAttendOne.tMember);
    	    	}
    	    	
    	    	if (tSendMember.size() > 0) {
    	    		//未提出者がいたら配信処理をする。
    	    		
    	    		//タイトルを作る
        	    	StringBuilder sb = new StringBuilder();
        	    	if (tPartyOne.deadlineHowNum.equals(TODAY)) {
        	    		sb.append("【本日締め切り】");
        	    	} else {
        	    		sb.append("(出欠席)　");
        	    	}
        	    	sb.append(tPartyOne.meetingName);
        	    	String title = new String(sb);
        	    	
        	    	//内容を作る
        	    	StringBuilder sbc = new StringBuilder();
        	    	sbc.append("このメールは、出欠をまだ出していないメンバーに自動配信されるメールです。");
        	    	sbc.append("\n");
        	    	sbc.append("\n");
        	    	sbc.append("会議名:　");
        	    	sbc.append(tPartyOne.meetingName);
        	    	sbc.append("\n");
        	    	sbc.append("会議内容: ");
        	    	sbc.append("\n");
        	    	sbc.append(tPartyOne.meetingMemo);
        	    	sbc.append("\n");
        	    	sbc.append("\n");
        	    	sbc.append("開催日: ");
        	    	if (tPartyOne.meetingDay != null) {
        	    		sbc.append(tPartyOne.meetingDay);
        	    	} else {
        	    		sbc.append("(未設定)");
        	    	}
        	    	sbc.append("\n");
        	    	sbc.append("開催時間: ");
        	    	if (tPartyOne.meetingTime != null) {
        	    		sbc.append(tPartyOne.meetingTime);
        	    	} else {
        	    		sbc.append("(未設定)");
        	    	}
        	    	sbc.append("\n");
        	    	sbc.append("締め切り日時: ");
        	    	sbc.append(tPartyOne.meetingDeadlineDay);
        	    	sbc.append("\n");
        	    	sbc.append("\n");
        	    	if (!tPartyOne.deadlineHowNum.equals(TODAY)) {
        	    		sbc.append("締め切りまで後");
        	    		sbc.append(tPartyOne.deadlineHowNum);
        	    		sbc.append("日です。");
        	    		sbc.append("\n");
        	    		sbc.append("以上のイベントにまだ出欠を出していません。とっとと出欠を出しましょう。");
        	    	} else {
        	    		if (tPartyOne.meetingNecessaryFlag) {
        	    			sbc.append("出席必須の会議のイベントにまだ出欠席を出していません。");
        	    			sbc.append("\n");
        	    		}
        	    		sbc.append("締め切りは本日です。");
        	    		sbc.append("\n");
        	    		sbc.append("可及的速やかに出席を出してください。");
        	    		sbc.append("\n");
        	    		sbc.append("今すぐ出してください");
        	    		sbc.append("\n");
        	    		sbc.append("つーかだせ。何のためにこのシステムを作ったと思ってるんだ（●｀□´●）(by dawachin)");
        	    	}
        	    	sbc.append("\n");
        	    	sbc.append("\n");
        	    	String content = new String(sbc);
        	    	
        	    	//メールを送信する
                	MailManagerUtil mailUtil = new MailManagerUtil();
                	mailUtil.setBrowsingRights(MailBrowsingRightsCode.MEMBER.getCodeNumber());
                	mailUtil.setTitle(title);
                	mailUtil.setContent(content);	
                	mailUtil.setLinkUrlFlag(false);
                	mailUtil.setToAddressActorSplit(tSendMember);
                	mailUtil.sendMail();
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
    	
    	//出欠必須であり締め切り日の3日前の会議一覧
    	List<TParty>  tPartyListHISSU_TREE = tPartyService.findBy_Deadline_PULS(dateNow, TREE_DAY, true);
    	sendMail(tPartyListHISSU_TREE);
    	
    	//出欠必須であり締め切り日の2日前の会議一覧
    	List<TParty>  tPartyListHISSU_TWO_TREE = tPartyService.findBy_Deadline_PULS(dateNow, TWO_DAY, true);
    	sendMail(tPartyListHISSU_TWO_TREE);
    		
    	//出欠必須であり締め切り日の1日前の会議一覧
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
