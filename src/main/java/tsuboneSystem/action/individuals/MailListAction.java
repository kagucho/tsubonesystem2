package tsuboneSystem.action.individuals;

import java.util.List;

import javax.annotation.Resource;

import tsuboneSystem.dto.LoginIndividualsDto;
import tsuboneSystem.entity.TMail;


public class MailListAction extends tsuboneSystem.action.admin.MailListAction{
	@Resource
	LoginIndividualsDto loginIndividualsDto;
	
	@Override
	protected List<TMail> getMailRecord() {
		//とりあえず20件だけ検索
		return tMailService.findbyCreateId(loginIndividualsDto.memberId, 20, 0);
	}
}
