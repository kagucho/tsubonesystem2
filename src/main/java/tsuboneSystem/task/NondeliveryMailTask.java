package tsuboneSystem.task;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.original.util.MailUtil;
import tsuboneSystem.service.TMemberService;

@Task
@CronTrigger(expression = "00 00 12 * * ?")
public class NondeliveryMailTask extends AbstractTask {
	@Resource
	TMemberService tMemberService;
	
	@Override
	String getTascName() {
		return "不到達メールを検索しフラグを立てる";
	}

	@Override
	void process() throws Exception {
		//メールが送れていない人を取得
		List<TMember> member = tMemberService.findByMailAddres(MailUtil.getFaledSendAddressList().toArray(new String[0]));
		
		//不到達フラグを書き換えてアップデートを行う
		for (TMember tMember : member) {
			tMember.sendErrorFlag = false;
			tMemberService.update(tMember);
		}
	}

}
