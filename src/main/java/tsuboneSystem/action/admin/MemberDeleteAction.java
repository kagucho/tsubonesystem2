package tsuboneSystem.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.code.ActorKindCode;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberForm;
import tsuboneSystem.original.util.TsuboneSystemUtil;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberClubService;
import tsuboneSystem.service.TMemberService;

public class MemberDeleteAction {

	public String actionName = "MemberDelete";

	/** Memberのアクションフォーム */
	@ActionForm
	@Resource
	protected MemberForm memberForm;

	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** TClubのサービスクラス */
	@Resource
	protected TClubService tClubService;

	/** TMemberClubServiceのサービスクラス */
	@Resource
	protected TMemberClubService tMemberClubService;

	/** TLeadersServiceのサービスクラス */
	@Resource
	protected TLeadersService tLeadersService;

	/** TAdminServiceのサービスクラス */
	@Resource
	protected TAdminService tAdminService;

	/** HttpServlet */
	@Resource
	protected HttpServletRequest request;

	/** Memberのリスト */
	public List<TMember> memberItems;

	/** Clubのリスト */
	public List<TClub> clubItems;

	@Execute(validator = false, urlPattern = "{id}", reset = "resetInput")
	public String index() {

		TMember member = tMemberService.findById(memberForm.id);
		Beans.copy(member, memberForm).execute();

		//マップの取得
        memberForm.clubMap = tClubService.getClubMapIS();

        //パスワードは表示しない
        memberForm.password = "(パスワードは初期化のみ可能です)";
    	//削除処理

    	member.deleteFlag = true;
    	tMemberService.update(member);

    return "memberComplete.jsp";
    }

  //オリジナルチェック
    public ActionMessages validateBase(){

        ActionMessages errors = new ActionMessages();

        //役職に就いていないか確認
        TMember member = tMemberService.findById(memberForm.id);
    	if (!TsuboneSystemUtil.actorKind(member).equals(ActorKindCode.MEMBER.getCode())) {
    		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため削除できません",false));
    	}

        return errors;
    }
}

