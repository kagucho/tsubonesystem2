package tsuboneSystem.batch.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;

import tsuboneSystem.entity.TParty;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TPartyClubService;
import tsuboneSystem.service.TPartyService;

/*
 * 会議締め切り確認時の自動メール送信
 * 起動時間は毎日午後18時
 * 
 * 
 * @CronTrigger(expression = "0 44 17 * * ?")：17時44分00秒に◯◯()メソッドを実行するよってこと
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
	
	/** TPartyClubServiceのサービスクラス */
	@Resource
	protected TPartyClubService tPartyClubService;

	// タスク処理
    public void doExecute() {
    	
    	//実行された時点で、締め切られていない会議の一覧を取得
    	Date dateNow = new Date();
    	List<TParty>  tPartyList = tPartyService.findBy_Deadline_GE_Now(dateNow);
    	
    	if (tPartyList.size() > 0) {
    		//締め切られていいない会議が存在したら以下を実行
    		for (TParty tPartyOne : tPartyList) {
    			if (tPartyOne.tPartyClubList.size() > 0) {
    				//出席対象が部によって限定されている場合
    			}else{
    				//出席対象が全員の場合
    			}
    			
    		}
    		
    	}
        
    }
    
}
