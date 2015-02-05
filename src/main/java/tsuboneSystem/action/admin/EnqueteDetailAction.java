package tsuboneSystem.action.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tsuboneSystem.dto.LoginMemberDto;
import tsuboneSystem.entity.TEnquete;
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.form.EnqueteForm;
import tsuboneSystem.original.util.TEnqueteSelectComparatorUtil;
import tsuboneSystem.service.TEnqueteAnswerService;
import tsuboneSystem.service.TEnqueteService;
import tsuboneSystem.service.TMailSendMemberService;
import tsuboneSystem.service.TMailService;
import tsuboneSystem.service.TMemberService;

public class EnqueteDetailAction {

	/** アクションネーム */
	public String actionName = "EnqueteDetail";

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

	public final int num = 1;

	@Execute(validator = false, urlPattern = "{id}")
	public String index() {

		//アンケートを取得する
		TEnquete tEnquete = tEnqueteService.findByIdJoinTable(enqueteForm.id);
		
		Beans.copy(tEnquete, enqueteForm).execute();
		
		// 演算
		compileDate(tEnquete);
		
		// 配色
		setColor(enqueteForm.tEnqueteSelectList);
		
		enqueteForm.userName = tEnquete.tMember.userName;

		return "enqueteDetail.jsp";
	}
	
	/** 
	 * 演算部
	 *  
	 *  
	 *  */
	public void compileDate (TEnquete tEnquete) {
		
		// 回答対象人数
		enqueteForm.respondMemberCount = tMemberService.countMember(false);
		
		// 回答人数
		int sum = 0;
		enqueteForm.tEnqueteSelectList = new ArrayList<TEnqueteSelect>();
		for (TEnqueteSelect tEnqueteSelect : tEnquete.tEnqueteSelect) {
			tEnqueteSelect.resultNum = tEnqueteAnswerService.resultNumCount(tEnqueteSelect.id);
			enqueteForm.tEnqueteSelectList.add(tEnqueteSelect);
			sum += Integer.parseInt(tEnqueteAnswerService.resultNumCount(tEnqueteSelect.id));
		}
		enqueteForm.totalCountNum = Integer.toString(sum); // 回答した人数
		
		// 回答率
		BigDecimal resMemberCountAll = BigDecimal.valueOf(Long.parseLong(enqueteForm.respondMemberCount)); // 回答対象全体の数
		BigDecimal resMemberNum = BigDecimal.valueOf(sum); // 回答総数
		BigDecimal resAllRate = BigDecimal.ZERO;
		if (!BigDecimal.ZERO.equals(resMemberNum)) {
			 resAllRate = resMemberNum.divide(resMemberCountAll, 2, BigDecimal.ROUND_HALF_UP); // 全体に対する回答率
		} 
		enqueteForm.resAllRate = resAllRate.toString(); // 回答対象人数に対する回答した人数の割合
		
		// 選択肢あたりの回答数に対する割合
		List <TEnqueteSelect> list = new ArrayList<TEnqueteSelect>();
		for (TEnqueteSelect tEnqueteSelect : enqueteForm.tEnqueteSelectList) {
			BigDecimal resultNum = BigDecimal.valueOf(Long.parseLong(tEnqueteSelect.resultNum)); // 選択肢を選んだ人数
			BigDecimal resSelectRate = BigDecimal.ZERO;
			if (!BigDecimal.ZERO.equals(resultNum) && !BigDecimal.ZERO.equals(resMemberNum)) {
				resSelectRate = resultNum.divide(resMemberNum, 2, BigDecimal.ROUND_HALF_UP); // 回答した人数に対する選択肢の割合
			}
			BigDecimal resSelectAllRate = BigDecimal.ZERO;
			if (!BigDecimal.ZERO.equals(resultNum)) {
				resSelectAllRate = resultNum.divide(resMemberCountAll, 2, BigDecimal.ROUND_HALF_UP); // 回答対象総人数に対する選択肢の割合
			}
			tEnqueteSelect.resultRate = resSelectRate.toString();
			tEnqueteSelect.allRate = resSelectAllRate.toString();
			list.add(tEnqueteSelect);
		}
		// 回答者数で並べ替え
		Collections.sort(list, new TEnqueteSelectComparatorUtil());
		enqueteForm.tEnqueteSelectDetailList = list;
	}
	
	/**
	 * 一つの選択肢に対して配色する
	 * 
	 * 
	 * */
	private void setColor (List<TEnqueteSelect> tEnqueteSelectList) {
		List<TEnqueteSelect> list = new ArrayList<TEnqueteSelect>();
		for (TEnqueteSelect tEnqueteSelect : tEnqueteSelectList) {
			tEnqueteSelect.r = (int)(Math.random()*256);
			tEnqueteSelect.g = (int)(Math.random()*256);
			tEnqueteSelect.b = (int)(Math.random()*256);
			list.add(tEnqueteSelect);
		}
		enqueteForm.tEnqueteSelectList = list;
	}
}