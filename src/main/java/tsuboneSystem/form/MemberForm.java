package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.framework.util.ArrayUtil;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.code.LeadersKindCode;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.entity.TTempLogin;
import tsuboneSystem.service.TAdminService;
import tsuboneSystem.service.TClubService;
import tsuboneSystem.service.TLeadersService;
import tsuboneSystem.service.TMemberService;
import tsuboneSystem.service.TTempLoginService;


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
	@DateType(datePatternStrict="yyyy",msg=@Msg(key="errors.dateYYYY", resource=true))
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
	
	/* メール受信可否　*/
	@Maxlength(maxlength=10)
	public String sendStopFlag;
	
	/* 仮登録メンバーフラグ　*/
	public String tempMemberFlag;
	
	/* ID　*/
	@Required(msg=@Msg(key="errors.id", resource=true))
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=30)
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
	
	public Integer carrentId;

	/** TMember **/
	public TMember tMemberOld;
	
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
	
	/** 仮登録メンバー認証フラグ **/
	public boolean approveFlag;

	
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
		sendStopFlag = "false";
		approveFlag = false;
    }
	
	//オリジナルチェック(Admin用)
    public ActionMessages validateBaseAdmin(){
    	
        ActionMessages errors = new ActionMessages();
        
        //基本確認3種
        basicCheckSet(errors);
		
		//役職に就いている人はOB宣言できない
		obCheck(errors);
		
		//役職に就いてるメンバーの連絡先を空白にはできない
		contactAddressRequiredCheck(errors);
		
        return errors;
    }
    
    //オリジナルチェック(Leaders用)
    public ActionMessages validateBaseLeaders(){
    	
        ActionMessages errors = new ActionMessages();
        
        //基本確認3種
        basicCheckSet(errors);
		
		//役職に就いている人はOB宣言できない
		obCheck(errors);
		
		//役職に就いてるメンバーの連絡先を空白にはできない
		contactAddressRequiredCheck(errors);
		
		//管理者の情報更新は管理者の権限を持っていないとできない
		adminUpdateCheck(errors);
		
        return errors;
    }
    
    //オリジナルチェック(一般メンバー用)
    public ActionMessages validateBaseInd(){
    	
        ActionMessages errors = new ActionMessages();
        
        //基本確認3種
        basicCheckSet(errors);
        
        return errors;
    }
    
    //オリジナルチェック(TempMember用)
    public ActionMessages validateBaseTemp(){
    	
        ActionMessages errors = new ActionMessages();
        
        //基本確認3種
        basicCheckSet(errors);
        
        return errors;
    }
    
    // 基本確認Set
    public void basicCheckSet(ActionMessages errors){
    	// userNameの重複チェック
    	userNameOverlapCheck(errors);
    
    	// メールアドレスの重複チェック
    	mailAddressOverlapCheck(errors);
    
    	//所属部の必須チェック
    	clabRequiredCheck(errors);
    }
    
    // userNameの重複チェック
    private void userNameOverlapCheck(ActionMessages errors){
    	// userNameの重複チェック
        TMemberService tMemberService = SingletonS2Container.getComponent(TMemberService.class);
        TMember tMemberRec = tMemberService.findByUserName(userName);	
        TTempLoginService tTempLoginService = SingletonS2Container.getComponent(TTempLoginService.class);
        TTempLogin tTempLogin = tTempLoginService.findByUserName(userName);
        
        if (tTempLogin != null) {
        	errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
        }else{
        	//新規登録用
        	if (id.equals(null)) {
            	if (tMemberRec != null || tTempLogin != null) {
            		errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
            	}
            //更新用
        	}else{
            	if (!id.equals(tMemberRec.id)) {
            		errors.add("userName",new ActionMessage("残念！！このログインIDはすでに使われています。",false));
            	}
        	}
        }
    }
    
    //メールアドレス重複チェック
    private void mailAddressOverlapCheck(ActionMessages errors){
    	TMemberService tMemberService = SingletonS2Container.getComponent(TMemberService.class);
    	TMember tMemberRec = tMemberService.findByEmail(mail);
    	//新規登録用
    	if (id.equals(null)) {
        	if (tMemberRec != null) {
        		errors.add("mail",new ActionMessage("残念！！このメールアドレスはすでに使われています。",false));
        	}
        //更新用
    	}else{
        	if (tMemberRec != null) {
        		if (!id.equals(tMemberRec.id)) {
            		errors.add("mail",new ActionMessage("残念！！このメールアドレスはすでに使われています。",false));
            	}
        	}
    	}
    }
    
    //所属部の必須チェック
    private void clabRequiredCheck(ActionMessages errors){
    	//所属部の必須チェック
    	if(ArrayUtil.isEmpty(clubListCheck) && clubListChecked.size() == 0){
    		errors.add("department",new ActionMessage("部の選択は必須です。",false));
    	}
    }
    
    //役職に就いている人はOB宣言できない
    private void obCheck(ActionMessages errors){
    	//役職に就いている人はOB宣言できない
		if("true".equals(obFlag)){
			TAdminService tAdminService = SingletonS2Container.getComponent(TAdminService.class);
			TAdmin tAdmin = tAdminService.findById(id);
			TLeadersService tLeadersService = SingletonS2Container.getComponent(TLeadersService.class);
			List<TLeaders> tLeadersList = tLeadersService.findByMemberIdList(id);
			if(tAdmin != null || tLeadersList.size() > 0){
				obFlag = "false";
				errors.add("obFlag",new ActionMessage("役職についている人はOB宣言出来ません。",false));
			}
		}
    }
    
    //選択されたMemberが現役の部長以上の役職に付いている場合、連絡先をすべて登録しているかを確認する。
    private void contactAddressRequiredCheck(ActionMessages errors){
    	//選択されたMemberが現役の部長以上の役職に付いている場合、連絡先をすべて登録しているかを確認する。
    	TLeadersService tLeadersService = SingletonS2Container.getComponent(TLeadersService.class);
    	List<TLeaders> tLeadersList = new ArrayList<TLeaders>();
    	if (id != null) {
    		tLeadersList = tLeadersService.findByMemberIdList(id);
        	if (tLeadersList.size() > 0) {
        		for (TLeaders tLeadersOne : tLeadersList) {
        			TClubService tClubService = SingletonS2Container.getComponent(TClubService.class);
        			List<TClub> tClub = tClubService.findByLeadersId(tLeadersOne.id);
        			if (tClub.size() > 0) {
        				//各部の現役の部長の場合
        				if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(tel1) || StringUtils.isEmpty(tel2) || StringUtils.isEmpty(tel3)) {
                    		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため、連絡先を空白にすることはできません",false));
                    	}
        			}else if (tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.GASSYUKU.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.RIDAISAI.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ETC.getCode())) || tLeadersOne.OfficerKind.equals(Integer.valueOf(LeadersKindCode.ACCOUNT.getCode()))) {
        				//部長以外の場合
        				if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(tel1) || StringUtils.isEmpty(tel2) || StringUtils.isEmpty(tel3)) {
                    		errors.add("OfficerCheck",new ActionMessage("このメンバーには部長以上の役職に付いているため、連絡先を空白にすることはできません",false));
                    	}
        			}
        		}
        	}
    	}
    }
    
    //管理者の情報は編集できない
    private void adminUpdateCheck(ActionMessages errors){
    	TAdminService tAdminService = SingletonS2Container.getComponent(TAdminService.class);
    	List<TAdmin> tAdminList = tAdminService.findByMemberIdList(id);
    	if(tAdminList.size() > 0){
    		errors.add("OfficerCheck",new ActionMessage("このメンバーは管理者であり、編集には権限が必要です。",false));
    	}
    }
}
