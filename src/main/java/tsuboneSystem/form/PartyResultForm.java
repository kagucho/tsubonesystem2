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
import org.seasar.framework.util.StringUtil;
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
		
		/** メールの送信可否　*/
		public boolean mailSendFlag;
		
		/** 現役生かOBか 1:現役生　2:OB */
		public String activeOrOb;
		
		/** 全員か部ごとか 1:全員 2:部ごと */
		public String allOrClub;
		
		/** 選択した部 */
		public String[] clubListCheck = new String[0];
		
		/** メールのタイトル　*/
		public String title;
		
		/** メールの内容　*/
		public String content;
		
		/** メールの送信相手一覧 */
		public List<TMember> tMemberSendList;
		
		/** メールの送信者　*/
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
		activeOrOb = null;
		allOrClub = null;
		resultEditEndFlag = false;
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
        //メール配信関係
        if (mailSendFlag) {
        	if (StringUtils.isEmpty(title)) {
        		errors.add("title",new ActionMessage("メールを送る場合は、メールの題名を入力してください。",false));
        	}
        	if (StringUtils.isEmpty(content)) {
        		errors.add("content",new ActionMessage("メールを送る場合は、メールの内容を入力してください。",false));
        	}
        	if (StringUtil.isNotEmpty(activeOrOb)) {
    			if ("1".equals(activeOrOb)) {
    				// 現役生の場合は全員か部ごとか
    				if (StringUtil.isNotEmpty(allOrClub)) {
    					if ("2".equals(allOrClub)) {
    						//　部ごとのメールの場合は部が選択されている必要がある
    						if ("1".equals(activeOrOb)) {
    							if (clubListCheck.length == 0) {
    								errors.add("clubListCheck",new ActionMessage("部を選択してください",false));
    							}
    						}
    					}
    				} else {
    					errors.add("allOrClub",new ActionMessage("送る範囲を選択してください",false));
    				}
    			}
    		} else {
    			errors.add("activeOrOb",new ActionMessage("送り相手を選択してください。",false));
    		}
        }

        return errors;
    }
	
}
