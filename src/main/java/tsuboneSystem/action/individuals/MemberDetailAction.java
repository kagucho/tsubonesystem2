package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.dto.LoginMemberDto;

public class MemberDetailAction extends tsuboneSystem.action.admin.MemberDetailAction{
	
	public String actionName = "MemberDetail";
	
	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	//ログイン者の情報
	@Resource
	protected LoginIndividualsDto loginIndividualsDto;
	
	//現在見ている情報が自分のものならTRUE
	public boolean isMyInfo = true;
	
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {
		//まずは詳細を表示するメンバーの情報を格納
		super.detail();
		
		//もし表示する情報が自分でなければ一部の情報を隠す
		if (!memberForm.id.equals(loginIndividualsDto.memberId)) {
			memberForm.password = "*****";
			memberForm.tel1 = "***";
			memberForm.tel2 = "****";
			memberForm.tel3 = "****";
			memberForm.userName = "*****";
			//フラグを書き換える
			isMyInfo = false;
		}
		
		return "memberDetail.jsp";
	}
}
