package tsuboneSystem.dto;

import java.io.Serializable;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class LoginLeadersDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/** ログインID **/
	public  Integer memberId;
	
	/** ログインしている人の情報 **/
	public TMember tMemberLogin;
	
	/** 役職の情報 **/
	public List<TLeaders> tLeadersList;
	
	/** 部長の場合の情報 **/
	public TClub tClub;
	
	/** 秘匿情報閲覧権限 **/
	public boolean secretInformation = false;
	
	/** メンバー情報更新権限 **/
	public boolean memberUpdate = false;
	
	/** 出欠変更権限 **/
	public boolean attendUpdate = false;
	
	/** 部情報更新権限 **/
	public boolean clubUpdate = false;
	
	/** リダイレクト先ＵＲＬ */
	public String redirectURL;
}
