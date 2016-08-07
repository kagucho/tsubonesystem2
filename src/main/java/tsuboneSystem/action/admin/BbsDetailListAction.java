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

package tsuboneSystem.action.admin;

import java.io.IOException;

import javax.annotation.Resource;

import org.markdown4j.Markdown4jProcessor;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TBbsDetail;
import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.form.BbsForm;
import tsuboneSystem.service.TBbsDetailService;
import tsuboneSystem.service.TBbsSubjectService;
import tsuboneSystem.service.TMemberService;

public class BbsDetailListAction {
	
	public String actionName = "Kagucho.BBS";
	
	/** BbsFormのアクションフォーム */
	@ActionForm
	@Resource
	protected BbsForm bbsForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TBbsSubjectServiceのサービスクラス */
	@Resource
	protected TBbsSubjectService tBbsSubjectService;
	
	/** TBbsDetailServiceのサービスクラス */
	@Resource
	protected TBbsDetailService tBbsDetailService;
	
	//選択されたスレ
	public TBbsSubject tBbsSubject;
	
    @Execute(validator = false, urlPattern = "{id}")
	public String index() {
    	bbsForm.tBbsDetailList = tBbsDetailService.findBySubjectId(bbsForm.id);
    	
    	return "BbsDetailList.jsp";
	}

    //confirmのバリデータに引っかかった時はここに戻ってくる。(入力した値保持のため)
    @Execute(validator = false)
	public String viewinput() {
    	return "BbsDetailList.jsp";
    }
    
    @Execute(validator = true,input = "viewinput")
	public String bbsDetailRegist() {
    	
    	//MarkDownに変換する
    	String markDown = new String();
    	Markdown4jProcessor pross = new Markdown4jProcessor();
		try {
			markDown = pross.process(bbsForm.detail);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//DBに書き込み
    	TBbsDetail tBbsDetail = new TBbsDetail();
    	tBbsDetail.subjectId = bbsForm.id;
    	tBbsDetail.detail = markDown;
    	tBbsDetail.memberId = loginMemberDto.memberId;
    	tBbsDetailService.insert(tBbsDetail);
    	bbsForm.detail = null;
    	
    return "/" + getDirectory() + "/bbsDetailList/?redirect=true";
	}
    
    protected String getDirectory() {
    	return "admin";
    }
}
