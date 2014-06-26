package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;


@Component(instance = InstanceType.SESSION) 
public class MailForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/* id　*/
	public Integer id;
	
	/* メールの送信可否　*/
	public boolean mailSendFlag;

	/* 全体へのメール送信　*/
	public String mailSendAllFlag;
	
	/* OBへのメール送信　*/
	public String mailSendOBFlag;

	/* 選択した部 */
	public String[] clubListCheck = new String[0];
	
	/* メールのタイトル　*/
	@Required
	public String title;
	
	/* メールの内容　*/
	@Required
	public String content;
	
	/* メールの送信相手　*/
	public Set<Integer> MemberSendSet = new HashSet<Integer>();
	
	/* メールの送信相手　*/
	public Set<TMember> MemberSendSet2 = new HashSet<TMember>();
	
	/* メールの送信者　*/
	public Integer registMemberId;
	
	/** Member一覧　*/
	public List<TMember> tMemberItem;
	
	/** 選ばれたmember　*/
	public String[] memberSelect;
	
	/** Member一覧　*/
	public List<TMember> tMemberSendList = new ArrayList<TMember>();
	
	/** 登録されている部一覧 */
	public List<TClub> clubList;

	/** 部のマップ */
	public HashMap<String, String> clubMapSS;
	
	/** 登録されている部一覧 */
	public String getContent() {
		return content; 
	}
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		clubListCheck = new String[0];
		mailSendFlag = false;
		mailSendAllFlag = null;
		mailSendOBFlag = null;
	}
	//オリジナルチェック
    public ActionMessages validateBase(){
       ActionMessages errors = new ActionMessages();
    return errors;
    }
}
