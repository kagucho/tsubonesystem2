package tsuboneSystem.action.admin;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginAdminDto;
import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TEnquete;
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.form.EnqueteForm;
import tsuboneSystem.service.TEnqueteSelectService;
import tsuboneSystem.service.TEnqueteService;

public class EnqueteRegistAction {

	/** アクションフォーム */
	@ActionForm
	@Resource
	public EnqueteForm enqueteForm;

	/** LoginMemberDto */
	@Resource
	public LoginMemberDto loginMemberDto;

	/** LoginAdminDto */
	@Resource
	public LoginAdminDto loginAdminDto;

	/** サービス */
	@Resource
	public TEnqueteService tEnqueteService;

	/** 選択肢サービス */
	@Resource
	public TEnqueteSelectService tEnqueteSelectService;

	@Execute(validator = false, reset = "resetInput")
	public String index() {

		return viewinput();
	}

	@Execute(validator = false, reset = "resetInput")
	public String viewinput() {
		return "enqueteInput.jsp";
	}

	@Execute(validator = true, input = "viewinput", reset = "resetInput", validate = "validateBase", stopOnValidationError = false)
	public String confirm() {
		return "enqueteConfirm.jsp";
	}

	@Execute(validator = false)
	public String complete() {
		//DBに新規登録
		TEnquete tEnquete = new TEnquete();
		Beans.copy(enqueteForm, tEnquete).execute();
		tEnquete.createId = loginMemberDto.memberId;
		tEnquete.memberId = loginMemberDto.memberId;
		tEnqueteService.insert(tEnquete);
		enqueteForm.id = tEnquete.id;

		//選択肢を入れる
		for (String selectedContents : enqueteForm.selectedContents) {

			TEnqueteSelect tEnqueteSelect = new TEnqueteSelect();
			tEnqueteSelect.enqueteId = tEnquete.id;
			tEnqueteSelect.selectedContents = selectedContents;
			tEnqueteSelectService.insert(tEnqueteSelect);

		}

		return "enqueteComplete.jsp";
	}
}
