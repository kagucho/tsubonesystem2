package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;


@Component(instance = InstanceType.SESSION) 
public class MemberForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* 名前　*/
	@Required(msg=@Msg(key="errors.name", resource=true))
	@Maxlength(maxlength=10)
	public String name;
	
	/* ハンドルネーム　*/
	@Required(msg=@Msg(key="errors.hname", resource=true))
	@Maxlength(maxlength=10)
	public String hname;
	
	/* 性別　*/
	@Required(msg=@Msg(key="errors.sex", resource=true))
	@Maxlength(maxlength=10)
	public String sex;
	
	/* メールアドレス　*/
	@Required(msg=@Msg(key="errors.mail", resource=true))
	@EmailType(msg=@Msg(key="errors.mailout", resource=true))
	public String mail;
	
	/* 所属学科　*/
	@Maxlength(maxlength=10)
	public String curriculum;
	
	/* 入学年度　*/
	@Required(msg=@Msg(key="errors.entrance", resource=true))
	@DateType(datePatternStrict="yyyy",msg=@Msg(key="errors.date", resource=true))
	public String entrance;
	
	/* 電話番号1　*/
	@Maxlength(maxlength=10)
	public String tel1;
	
	/* 電話番号2　*/
	@Maxlength(maxlength=10)
	public String tel2;
	
	/* 電話番号3　*/
	@Maxlength(maxlength=10)
	public String tel3;
	
	/* OBフラグ　*/
	@Maxlength(maxlength=10)
	public String obFlag;
	
	/* ID　*/
	@Required(msg=@Msg(key="errors.id", resource=true))
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=10)
	public String userName;
	
	/* メール不達フラグ　*/
	public boolean sendErrorFlag;
	
	/* 削除フラグ　*/
	public String deleteFlag;
	
	/* パスワード　*/
	@Required(msg=@Msg(key="errors.password", resource=true),target = "confirm")
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=30)
	public String password;

	/** TMember **/
	public TMember tMember;
	
	/** 部のリスト **/
	public List<TClub> clubList;
	
	/** 部のマップ **/
	public Map<Integer, String> clubMap;
	
	/** 部のマップ **/
	public Map<String, String> clubMapSS;
	
	/** 性別のマップ **/
	public Map<String, String> sexMap;
	
	/** 選択した部 **/
	public String[] clubListCheck = new String[0];
	
	/** 選択されていた部(更新時に使用) **/
	public List<String> clubListChecked = new ArrayList<String>();
	
	/** メンバーが所属している部のIDのリスト **/
	public List<TMemberClub> tMemberClubList;
	
	/** メンバーが所属している部のIDのリスト(completeで使用) **/
	public List<TMemberClub> tMemberClubUpOldId;

	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		name = null;
		hname = null;
		sex = null;
		mail = null;
		curriculum = null;
		entrance = null;
		tel1 = null;
		tel2 = null;
		tel3 = null;
		userName = null;
		password = null;
		clubListCheck = new String[0];
		clubListChecked = new ArrayList<String>();
		obFlag = null;
		sendErrorFlag = false;
    }

}
