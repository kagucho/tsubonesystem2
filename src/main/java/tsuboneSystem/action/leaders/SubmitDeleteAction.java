
package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TSubmit;


/**
 * 
 * 作品 登録
 * @author Hiroaki
 * 
 * */
public class SubmitDeleteAction extends tsuboneSystem.action.admin.SubmitDeleteAction{
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** 編集権限
	 * 
	 *  */
	@Override
	public boolean getIsEdit(TSubmit tSubmit) {
		return true;
	}
}
