package tsuboneSystem.action.leaders;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.service.TLeadersService;


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
	
	/** TLeadersService */
	@Resource
	public TLeadersService tLeadersService;
	
	
	/** 編集権限
	 *TODO 部長でかつ、自分の配下のメンバーのみ編集権限を与える処理を作る
	 *  */
	@Override
	public boolean getIsEdit(TSubmit tSubmit) {
		return true;
	}
}
