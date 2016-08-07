/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tsuboneSystem.task;

import java.util.ArrayList;
import java.util.HashSet;
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
		HashSet<String> sendErrorset  = MailUtil.getFaledSendAddressList();
		List<TMember> member = new ArrayList<TMember>();
		for(String one : sendErrorset){
			member.add(tMemberService.findByEmail(one));
		}
		
		//不到達フラグを書き換えてアップデートを行う
		for (TMember tMember : member) {
			tMember.sendErrorFlag = true;
			tMemberService.update(tMember);
		}
	}

}
