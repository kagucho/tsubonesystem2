/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package tsuboneSystem.action.admin;

import java.util.HashMap;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MailListForm;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class MailListAction {
	
	/** actionの名前　*/
	public String actionName = "MailList";
	
	/** MailListのアクションフォーム */
	@ActionForm
	@Resource
	protected MailListForm mailListForm;
	
	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;
	
	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	
    @Execute(validator = false)
	public String index() {
    	
    	//名前を表示するためのマップ(OBを含む)
    	mailListForm.memberMapIS = new HashMap<Integer,String>();
    	mailListForm.tMemberAllList = tMemberService.findAllOrderById(true);
    	for (TMember memberOne : mailListForm.tMemberAllList) {
    		mailListForm.memberMapIS.put(memberOne.id, memberOne.hname);	
    	}
    	
    	//メールの一覧
    	mailListForm.tMailItem = tMailService.findAllOrderById();
    	
    	
        return "index.jsp";
	}
}
