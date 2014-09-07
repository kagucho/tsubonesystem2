package tsuboneSystem.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION)
public class LoginMemberDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** ログインID **/
	public  Integer memberId;
	
	/** ログインされている権限(Code) **/
	public Integer actorKindCode;
	
	/** ログインされている権限 **/
	public String actorKind;
	
	/** ログインしている人の情報 **/
	public TMember tMemberLogin;
	
	/** リダイレクト先ＵＲＬ */
	public String redirectURL;
	
}
