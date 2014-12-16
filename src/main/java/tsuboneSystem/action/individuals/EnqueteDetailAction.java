package tsuboneSystem.action.individuals;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TEnquete;
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.form.EnqueteForm;
import tsuboneSystem.service.TEnqueteAnswerService;
import tsuboneSystem.service.TEnqueteService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class EnqueteDetailAction {

	/** アクションネーム */
	public String actionName = "ClubDetail";

	/** ClubFormのアクションフォーム */
	@ActionForm
	@Resource
	protected EnqueteForm enqueteForm;

	/** Member用のDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** TMemberのサービスクラス */
	@Resource
	protected TMemberService tMemberService;

	/** TEnqueteのサービスクラス */
	@Resource
	protected TEnqueteService tEnqueteService;

	/** TEnqueteAnswerServiceのサービスクラス */
	@Resource
	protected TEnqueteAnswerService tEnqueteAnswerService;

	/** TMailのサービスクラス */
	@Resource
	protected TMailService tMailService;

	/** TMailSendMemberServiceのサービスクラス */
	@Resource
	protected TMailSendMemberService tMailSendMemberService;

	/** ログインユーザ*/
	@Resource
	protected LoginAdminDto loginAdminDto;

	public final int num = 1;

	@Execute(validator = false, urlPattern = "{id}")
	public String index() {

		//アンケートを取得する
		TEnquete tEnquete = tEnqueteService.findByIdJoinTable(enqueteForm.id);
		//アンケートに答えた人の総数を計算
		int sum = 0;
		enqueteForm.tEnqueteSelectList = new ArrayList<TEnqueteSelect>();
		for (TEnqueteSelect tEnqueteSelect : tEnquete.tEnqueteSelect) {
			tEnqueteSelect.resultNum = tEnqueteAnswerService.resultNumCount(tEnqueteSelect.id);
			enqueteForm.tEnqueteSelectList.add(tEnqueteSelect);
			sum += Integer.parseInt(tEnqueteAnswerService.resultNumCount(tEnqueteSelect.id));
		}
		//アンケートに答えた人の総数をStringに変換
		tEnquete.totalCountNum = Integer.toString(sum);

		Beans.copy(tEnquete, enqueteForm).execute();
		enqueteForm.selectedContents = new String [tEnquete.tEnqueteSelect.size()];
		int i = 0;
		for (TEnqueteSelect tEnqueteSelect : tEnquete.tEnqueteSelect) {
			enqueteForm.selectedContents[i] = tEnqueteSelect.selectedContents;
			i++;
		}
		enqueteForm.userName = tEnquete.tMember.userName;

		return "enqueteDetail.jsp";
	}
}