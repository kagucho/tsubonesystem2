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

package tsuboneSystem.mail;

import java.util.List;

import javax.annotation.Resource;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TPartySendMail;
import tsuboneSystem.original.manager.MailManager;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TPartySendMailService;

public class MailRegistParty {
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	public TMailSendMemberService tMailSendMemberService;
	
	/** TPartySendMailServiceのサービスクラス */
	@Resource
	public TPartySendMailService tPartySendMailService;
	
	boolean errorFlag;
	
	@SuppressWarnings("deprecation")
	public boolean mailSend(List<TMember> tSendMember, String title, String content, Integer registMemberId, Integer partyId){
		
		//内容を作る(最後に改行をつけると文字化けを防げる)
    	StringBuilder sbc = new StringBuilder();
    	sbc.append(content);
    	sbc.append("\n");
    	String sendContent = new String(sbc);
		
    	//メールを送信する
    	MailManager manager = new MailManager();
    	manager.setTitle(title);
    	manager.setContent(sendContent);
    	manager.setToAddress(tSendMember.toArray(new TMember[0]));
    	if (manager.sendMail()) {
    		errorFlag = false;
    	} else {
    		errorFlag = true;
    	}
    	
    	//TMailにメールの内容を追加する
    	TMailService tMailService = new TMailService();
    	TMail tMail = new TMail();
    	tMail.title = title;
    	tMail.content = content;
    	tMail.registMemberId = registMemberId;
    	tMail.errorFlag = errorFlag;
    	tMailService.insert(tMail);
    	
    	//TMailSendAttendにメールの送信相手を追加する
    	for (TMember tMemberOne : tSendMember) {
    		TMailSendMember tMailSendMember = new TMailSendMember();
    		tMailSendMember.mailId = tMail.id;
    		tMailSendMember.memberId = tMemberOne.id;
    		tMailSendMemberService.insert(tMailSendMember);
    	}
    	
    	//会議とメールを紐付けるDBに登録する
    	TPartySendMail tPartySendMail = new TPartySendMail();
    	tPartySendMail.PartyId = partyId;
    	tPartySendMail.MailId = tMail.id;
    	tPartySendMailService.insert(tPartySendMail);
    	
		return errorFlag;
	}

}
