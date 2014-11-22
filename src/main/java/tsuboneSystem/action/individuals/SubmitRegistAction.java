package tsuboneSystem.action.individuals;

/**
 * 
 * 作品 登録
 * @author Hiroaki
 * 
 * */
public class SubmitRegistAction extends tsuboneSystem.action.admin.SubmitRegistAction{
	
	// 一般メンバーは自分の作品のみ
	@Override
	public String index() {
		submitForm.registId = loginMemberDto.memberId.toString();
		return super.index();
	}
	
}
