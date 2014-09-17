package tsuboneSystem.form;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TPartyClub;

@Component(instance = InstanceType.SESSION) 
public class PartyResultForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** 会議の登録者Id　*/
	public Integer creatorId;
	
	/** 会議名　*/
	@Required(msg=@Msg(key="errors.meetingName", resource=true))
	public String meetingName;
	
	/** 審議結果 */
    public String  meetingResult;
    
    /** 会議結果の最終編集者　*/
	public Integer resultEditMemberId;
    
    /** 最終編集フラグ */
    public boolean  resultEditEndFlag;
	
	/** 削除フラグ */
    public String  deleteFlag;

	
	//以下の項目はメール配信関係
		
		/* メールの送信可否　*/
		public boolean mailSendFlag;
	
		/* 全体へのメール送信　*/
		public String mailSendAllFlag;
		
		/* OBへのメール送信　*/
		public String mailSendOBFlag;
	
		/* 選択した部 */
		public String[] clubListCheck = new String[0];
		
		/* メールのタイトル　*/
		public String title;
		
		/* メールの内容　*/
		public String content;
		
		/* メールの送信相手一覧 */
		public List<TMember> tMemberSendList;
		
		/* メールの送信者　*/
		public Integer registMemberId;
		
	//メール配信項目ここまで	


	/** 部のリスト **/
	public List<TClub> clubList;
	
	/** 出席対象部のリスト **/
	public List<TPartyClub> tPartyClubList;
	
	/** 部のマップ **/
	public Map<String, String> clubMapSS;
	
	/** 部のマップ **/
	public Map<Integer, String> clubMapIS;
	
	/** 出席対象が部で選択されている場合　**/
	public Set<Integer> MemberSet = new HashSet<Integer>();

	
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		clubListCheck = new String[0];
		mailSendFlag = false;
		mailSendAllFlag = null;
		mailSendOBFlag = null;
		resultEditEndFlag = false;
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
        //メール配信関係
        if (mailSendFlag) {
        	if (mailSendAllFlag == null && clubListCheck.length == 0 ) {
        		errors.add("sendTo",new ActionMessage("メールを送る場合は、送り相手を選択してください。",false));
        	}
        	if (StringUtils.isEmpty(title)) {
        		errors.add("title",new ActionMessage("メールを送る場合は、メールの題名を入力してください。",false));
        	}
        	if (StringUtils.isEmpty(content)) {
        		errors.add("content",new ActionMessage("メールを送る場合は、メールの内容を入力してください。",false));
        	}
        	
        }

        return errors;
    }
	
}
