package tsuboneSystem.original.util;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.SingletonS2Container;
import tsuboneSystem.code.ActorKindCode;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TLeadersService;

/**
 *
 *このシステムで使う様々なUtil
 *
 *
 */
public class TsuboneSystemUtil {
	
	
	public String actorKind(TMember member){
		
		String actorKind = null;
		
		//メンバーに該当がいたら、そのメンバーが役職に付いているかを判定する
		List<TLeaders> leaderList = new ArrayList<TLeaders>();
		TLeadersService tLeadersService = SingletonS2Container.getComponent(TLeadersService.class);
		leaderList = tLeadersService.findByMemberIdOrderKindList(member.id);
		
		//メンバーに該当がいたら、そのメンバーが管理者に付いているかを判定する
		TAdminService tAdminService = SingletonS2Container.getComponent(TAdminService.class);
		TAdmin tAdmin = tAdminService.findByMemberId(member.id);
		
		//管理者の場合はadmin
		if(tAdmin != null){
			actorKind = ActorKindCode.ADMIN.getCode();
    		return actorKind;
		}
    	//部長以上の場合はleaders
    	if (leaderList.size() > 0) {
    		actorKind = ActorKindCode.LEADERS.getCode();
        	return actorKind;
    	}else{
    		actorKind = ActorKindCode.MEMBER.getCode();
        	return actorKind;
    	}
	}
}
