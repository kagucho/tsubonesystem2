package tsuboneSystem.action.admin;

import java.util.HashMap;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.SexCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberDetailAction {
	
	public String actionName = "MemberDetail";
	
	/** Memberフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;
	
	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;
	
	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;
	
	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;
	
	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;

	
	@SuppressWarnings("boxing")
	@Execute(validator = false, urlPattern = "detail/{id}")
	public String detail() {

        memberForm.clubMap = tClubService.getClubMapIS();
        
        /** Idから対象のメンバー情報を検索する　**/
        TMember memberDetail = new TMember();
        memberDetail = tMemberService.findById(memberForm.id);
        //検索した結果をformにコピー
        Beans.copy(memberDetail, memberForm).execute();	

        memberForm.sexMap = new HashMap<String, String>();
        for (Integer i=1; i<=3; i++) {
        	memberForm.sexMap.put(i.toString(), SexCode.getnameByCode(i.toString()));
        }
        
        
        /** Idから対象のメンバーが所属している部の一覧を検索する　**/
        memberForm.tMemberClubList = tMemberClubService.findByMemberId(memberForm.id.toString());
        
        //パスワードは表示しない
        memberForm.password = "(パスワードは初期化のみ可能です)";
        	
        return "memberDetail.jsp";
	}
}
