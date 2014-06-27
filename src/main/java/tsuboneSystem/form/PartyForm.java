package tsuboneSystem.form;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TPartyClub;

@Component(instance = InstanceType.SESSION) 
public class PartyForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** 会議の登録者Id　*/
	public Integer creatorId;
	
	/** 会議名　*/
	@Required(msg=@Msg(key="errors.meetingName", resource=true))
	public String meetingName;
	
	/** 会議の必須判定　*/
	public boolean meetingNecessaryFlag;
	
	/** 出席対象者　*/
	public String[] attendClub = new String[0];
	
	/** 会議日時　*/
	@Maxlength(maxlength=10)
	@DateType(datePatternStrict="yyyy/MM/dd",msg=@Msg(key="errors.date", resource=true))
	public String meetingDay;
	
	/** 会議時間　*/
	@DateType(datePattern = "HH:mm",msg=@Msg(key="errors.time", resource=true))
	public String meetingTime;
	
	/** 会議場所　*/
	public String meetingRoom;
	
	/** 会議内容　*/
	@Required(msg=@Msg(key="errors.meetingMemo", resource=true))
	public String meetingMemo;
	
	/** 会議出欠席締め切り日　*/
	@DateType(datePatternStrict="yyyy/MM/dd",msg=@Msg(key="errors.date", resource=true))
	public String meetingDeadlineDay;
	
	
	/** 審議結果 */
    public String  meetingResult;
	
	/** 削除フラグ */
    public String  deleteFlag;
    
    /** 期限切れフラグ */
    public boolean deadFlag;
    
    /** OB出席フラグ */
    public boolean ObAttendFlag;
	
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
		
		/* メールの送信相手　*/
		public Set<Integer> MemberSendSet = new HashSet<Integer>();
		
		/* メールの送信相手　*/
		public Set<TMember> MemberSendSet2 = new HashSet<TMember>();
		
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
		meetingName = null;
		meetingDay = null;
		meetingTime = null;
		meetingDeadlineDay = null;
		meetingRoom = null;
		meetingMemo = null;
		clubListCheck = new String[0];
		attendClub = null;
		mailSendFlag = false;
		mailSendAllFlag = null;
		mailSendOBFlag = null;
		ObAttendFlag = false;
		meetingDeadlineDay = null;
		meetingNecessaryFlag = false;
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
        ActionMessages errors = new ActionMessages();
        
        //メール配信関係
        if (mailSendFlag) {
        	if (mailSendAllFlag == null && clubListCheck.length == 0 ) {
        		errors.add("sendTo",new ActionMessage("メールを送る場合は、送り相手を選択してください。",false));
        	}
        	if (title.isEmpty()) {
        		errors.add("title",new ActionMessage("メールを送る場合は、メールの題名を入力してください。",false));
        	}
        	if (content.isEmpty()) {
        		errors.add("content",new ActionMessage("メールを送る場合は、メールの内容を入力してください。",false));
        	}
        	
        }
		
		//会議日は過去にはできない
		if (!meetingDay.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		    Date mDay = null;
		    Date dateNow = new Date();
		    
		    // 日付を作成します。
		    try {
		        mDay = sdf.parse(meetingDay);
		    } catch (ParseException e) {
//		        e.printStackTrace();
		    }
			
		    // 日付をlong値に変換します。
		    long dateTimeMDay = mDay.getTime();
		    long now = dateNow.getTime();
		    
		   
		    if (dateTimeMDay < now) {
		    	errors.add("meetingDay",new ActionMessage("過去に会議を予定したければタイムマシンを作ってからにしてください",false));
		    }
		}
		
		//締め切り日を過去にはできない
		if (!meetingDeadlineDay.isEmpty()) {
			
			//締め切りが設定されている時は開催日を空白にできない
			if (meetingDay.isEmpty()) {
				errors.add("meetingDay",new ActionMessage("締め切りが決まっている場合には、開催日は必須です",false));
			}
			if (meetingTime.isEmpty()) {
				errors.add("meetingTime",new ActionMessage("締め切りが決まっている場合には、開催時間は必須です",false));
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		    Date dDay = null;
		    Date dateNow = new Date();
				    
		    // 日付を作成します。
		    try {
		        dDay = sdf.parse(meetingDeadlineDay);
		        
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
					
		    // 日付をlong値に変換します。
		    
		    long dateTimeDDay = dDay.getTime();
		    long now = dateNow.getTime();
			
		   	if (dateTimeDDay < now) {
		   		errors.add("meetingDeadlineDay",new ActionMessage("過去に会議を予定したければタイムマシンを作ってからにしてください",false));
	    	}
		}
		
		//会議日と締め切り日の間は五日以上離れていなければならない
		if (!meetingDeadlineDay.isEmpty() && !meetingDay.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		    Date mDay = null;
		    Date dDay = null;
		    
		    // 日付を作成します。
		    try {
		        dDay = sdf.parse(meetingDeadlineDay);
		        mDay = sdf.parse(meetingDay);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
			
		    // 日付をlong値に変換します。
		    long dateTimeMDay = mDay.getTime();
		    long dateTimeDDay = dDay.getTime();
		    
		    //開催日と締め切り日の差を取り割る
		    if (dateTimeMDay > dateTimeDDay) {
			   	 long dayDiff = ( dateTimeMDay - dateTimeDDay  ) / (1000 * 60 * 60 * 24);
			   	 if (dayDiff < 5) {
			   		 errors.add("meetingDay",new ActionMessage("会議の開催日と締め切り日は5日以上離れていなければなりません",false));
			   		 errors.add("meetingDeadlineDay",new ActionMessage("会議の開催日と締め切り日は5日以上離れていなければなりません",false));
			   	 }
		    }else{
		    	errors.add("meetingDay",new ActionMessage("締め切りの方が開催日より後になるとか意味がわかりません",false));
			    errors.add("meetingDeadlineDay",new ActionMessage("締め切りの方が開催日より後になるとか意味がわかりません",false));
			}
		}
		
		//日時の空白確認(開催日)
		if (meetingDay.isEmpty() && !meetingTime.isEmpty()) {
			//どちらか一方を空白にはできない
			errors.add("meetingDay",new ActionMessage("日時はどちらかを空白にはできません",false));
		}else if (!meetingDay.isEmpty() && meetingTime.isEmpty()) {
			//どちらか一方を空白にはできない
			errors.add("meetingTime",new ActionMessage("日時はどちらかを空白にはできません",false));
		}
	
        return errors;
    }
	
}
