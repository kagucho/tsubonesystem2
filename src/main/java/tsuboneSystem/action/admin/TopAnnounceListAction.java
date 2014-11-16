
package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.form.TopAnnounceListForm;
import tsuboneSystem.service.TTopAnnounceService;


/**
 * 
 * TOPお知らせ 一覧
 * @author Hiroaki
 * 
 * */
public class TopAnnounceListAction {
	
	/** アクションネーム */
	public String actionName = "AnnounceList";
	
	/** アクションフォーム */
	@Resource
	@ActionForm
	public TopAnnounceListForm topAnnounceListForm;
	
	/** TTopAnnounceService */
	@Resource
	public TTopAnnounceService tTopAnnounceService;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** 一覧画面 */
	@Execute(validator = false)
	public String index() {
		
		// 一覧の取得
		topAnnounceListForm.list = tTopAnnounceService.findAll();

		return "topAnnounceList.jsp";
	}
}
