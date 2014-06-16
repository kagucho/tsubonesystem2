package tsuboneSystem.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.entity.TPartyClub;
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
 * @CronTrigger(expression = "0 44 17 * * ?")：17時44分00秒にdoExecute()メソッドを実行するよってこと
 */


@Task
@CronTrigger(expression = "0 00 18 * * ?")
public class PartyMailTask {
	
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
	public List<TMember> tMemberKuzu;

	/** 既出欠者 */
	public List<TPartyAttend> tAttendOn;
	
	/** 未出欠者 */
	public Map<String, String> mapKuzuSS;
	
	/** 出席対象者 */
	public List<TMember> tSendMember = new ArrayList<TMember>();
	
	public boolean errorFlag;
	
	
	// タスク処理
    public void doExecute() {
    	
    	//実行された時点で、締め切られていない会議の一覧を取得
    	Date dateNow = new Date();
    	List<TParty>  tPartyList = tPartyService.findBy_Deadline_GE_Now(dateNow);
    	
    	if (tPartyList.size() > 0) {
    		//締め切られていいない会議が存在したら以下を実行
    		for (TParty tPartyOne : tPartyList) {
    			//全員のリストを取得し、以下の処理の中で該当のメンバーは削除してき、残ったメンバーが出欠席を返さないクズ。
    	    	if (tPartyOne.ObAttendFlag){
    	    		//OBの出席を含む
    	    		if (tPartyOne.tPartyClubList.size() > 0) {
    	    			//部によって出席対象が絞られている場合
    	    			List<TPartyClub> tPartyClub = tPartyClubService.findByPartyId(tPartyOne.id);
    	    			Set<TMember> tMemberSet = new HashSet<TMember>();
    	    			for (TPartyClub tPartyClubOne : tPartyClub) {
    	    				for (TMemberClub tMemberClubOne : tPartyClubOne.tClub.tMemberClubList) {
    	    					tMemberSet.add(tMemberClubOne.tMember);
    	        				}
    	    			}
    	    			tMemberKuzu = new ArrayList<TMember>();
    	    			for (TMember tMember : tMemberSet) {
    	    				tMemberKuzu.add(tMember);
    	    			}
    	    		}else{
    	    			//全員が出席対象
    	    			tMemberKuzu = tMemberService.findByIdAll();
    	    		}	
    	    	}else{
    	    		//OBの出席を含まない
    	    		if (tPartyOne.tPartyClubList.size() > 0) {
    	    			//部によって出席対象が絞られている場合
    	    			List<TPartyClub> tPartyClub = tPartyClubService.findByPartyIdNoOB(tPartyOne.id);
    	    			Set<TMember> tMemberSet = new HashSet<TMember>();
    	    			for (TPartyClub tPartyClubOne : tPartyClub) {
    	    				for (TMemberClub tMemberClubOne : tPartyClubOne.tClub.tMemberClubList) {
    	    					tMemberSet.add(tMemberClubOne.tMember);
    	        				}
    	    			}
    	    			tMemberKuzu = new ArrayList<TMember>();
    	    			for (TMember tMember : tMemberSet) {
    	    				tMemberKuzu.add(tMember);
    	    			}
    	    		}else{
    	    			//全員が出席対象
    	    			tMemberKuzu = tMemberService.findByIdNoOBAll();
    	    		}	
    	    	}
    	    	
    	    	mapKuzuSS = new HashMap<String,String>();
    	    	
    	    	for (TMember memberKuzuOne : tMemberKuzu) {
    	    		mapKuzuSS.put(memberKuzuOne.id.toString(), memberKuzuOne.hname);
    	    	}
    	    	
    	    	//出席している人のリスト
    	    	tAttendOn = tPartyAttendService.findByPartyIdAndAttendOn(tPartyOne.id);
    	    	for (TPartyAttend attendOn : tAttendOn){
    	    		mapKuzuSS.remove(attendOn.memberId.toString());	
    	    	}
    	    	
    	    	//欠席している人のリスト
    	    	tAttendOn = tPartyAttendService.findByPartyIdAndAttendOff(tPartyOne.id);
    	    	for (TPartyAttend attendOff : tAttendOn){
    	    		mapKuzuSS.remove(attendOff.memberId.toString());
    	    	}
    			
    	    	for (String key : mapKuzuSS.keySet()) {
    	    		tSendMember.add(tMemberService.findById(Integer.valueOf(key)));
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
    	    	sbc.append("開催日時: ");
    	    	sbc.append(tPartyOne.meetingDay);
    	    	sbc.append(tPartyOne.meetingTime);
    	    	sbc.append("\n");
    	    	sbc.append("開催日時: ");
    	    	sbc.append(tPartyOne.meetingDeadlineDay);
    	    	sbc.append("\n");
    	    	sbc.append("\n");
    	    	sbc.append("以上の会議にまだ出欠を出していません。とっとと出欠を出しましょう。");
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
    
}
