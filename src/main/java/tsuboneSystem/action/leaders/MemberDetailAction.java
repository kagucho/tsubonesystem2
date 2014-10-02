package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginLeadersDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.service.TLeadersService;

public class MemberDetailAction extends tsuboneSystem.action.admin.MemberDetailAction{
	
	public String actionName = "MemberDetail";
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	//ログイン者の情報
	@Resource
	public LoginLeadersDto loginLeadersDto;
	
	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;
	
	//現在見ている情報が自分のものならTRUE
	public boolean isMyInfo = true;
	
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {
		//まずは詳細を表示するメンバーの情報を格納
		super.detail();
		
		if (!memberForm.id.equals(loginLeadersDto.memberId)) {
			//秘匿情報の権限がない場合
			if(!loginLeadersDto.secretInformation){
				//もし表示する情報が自分でなければ一部の情報を隠す
				memberForm.password = "*****";
				memberForm.tel1 = "***";
				memberForm.tel2 = "****";
				memberForm.tel3 = "****";
				memberForm.userName = "*****";
			}
			//フラグを書き換える
			isMyInfo = false;
		}
		return "memberDetail.jsp";
	}
}
