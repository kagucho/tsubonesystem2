package tsuboneSystem.action.individuals;

import java.util.List;

import javax.annotation.Resource;

import tsuboneSystem.code.MailBrowsingRightsCode;
import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TMail;


public class MailListAction extends tsuboneSystem.action.admin.MailListAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;

	@Override
	protected List<TMail> getMailRecord() {
		//とりあえず20件だけ検索
		return tMailService.findAllOrderByIdLimitOffset(MailBrowsingRightsCode.MEMBER.getCodeNumber(), 20, 0);
	}
	@Override
	protected List<TMail> getTMailSendMemberlRecord() {
		return tMailSendMemberService.findAllOrderByIdLimitOffset(mailListForm, loginMemberDto.memberId, MailBrowsingRightsCode.MEMBER.getCodeNumber(), 20, 0);
	}
}
