package tsuboneSystem.action.individuals;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TSubmit;


/**
 * 
 * 作品 詳細
 * @author Hiroaki
 * 
 * */
public class SubmitDetailAction extends tsuboneSystem.action.admin.SubmitDetailAction{
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** 編集権限
	 * 
	 *  */
	@Override
	public boolean getIsEdit(TSubmit tSubmit) {
		if (tSubmit.registId.equals(loginMemberDto.memberId)) {
			return true;
		}
		return false;
	}
}
