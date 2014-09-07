package tsuboneSystem.dto;

import java.io.Serializable;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class LoginAdminDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/** ログインID **/
	public  Integer memberId;
	
	/** ログインしている人の情報 **/
	public TMember tMemberLogin;
	
	/** 役職の情報 **/
	public List<TLeaders> tLeadersList;
	
	/** 役職の情報(複数の場合は一番上位の役職種) **/
	public Integer memberKind;
	
	/** 部長の場合の情報 **/
	public TClub tClub;
	
	/** リダイレクト先ＵＲＬ */
	public String redirectURL;
		
}
