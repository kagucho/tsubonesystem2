package tsuboneSystem.original.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.Query;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.data.extensions.Where;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 *
 * GoogleCalendar APIで実行
 * @version 1.0.0
 */
public class GoogleCalMain {

    // Google アカウント
    private static String GOOGLE_ACCOUNT = "kagutyo@gmail.com";
    
    // Google アカウントのパスワード
    private static String GOOGLE_PASSWORD = "hosys1019";
    
    // 送信URL
    private static String GOOGLE_CAL_URL = "http://www.google.com/calendar/feeds/default/private/full";
    
    //タイトル
    String title = "javaからの登録";
    
    //場所
    String place = "鹿児島";
    
    //内容
    String memo =  "テスト";
    
    //開始時間
    DateTime startTime = null;
    
    //終了時間
    DateTime endTime = null;
    
    /**
	 * タイトルを追加する。
	 * @param title
	 */
    public void setTitle(String title) {
		this.title = title;
	}
    
    /**
	 * 場所を追加する。
	 * @param place
	 */
    public void setplace(String place) {
		this.place = place;
	}
    
    /**
	 * 内容を追加する。
	 * @param memo
	 */
    public void setMemo(String memo) {
		this.memo = memo;
	}
    
    /**
	 * 開始時間を追加する。
	 * @param startTime
	 */
    public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
    
    /**
	 * 終了時間を追加する。
	 * @param endTime
	 */
    public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
    
    /**
     * Google カレンダーAPIを使用して、登録と参照
     * @param args
     */
    public void main() {

        GoogleCalMain main = new GoogleCalMain();
        main.googleCal();

    }

    /**
     * Google カレンダーAPIを使用して、
     * 登録と登録したデータの参照を行います。
     */
    private void googleCal() {
        
        try {
            URL postURL = new URL(GOOGLE_CAL_URL);

            // イベント登録クラス
            CalendarEventEntry myEntry = new CalendarEventEntry();
            // スケジュールのタイトル
            myEntry.setTitle(new PlainTextConstruct(title));
            // スケジュールの詳細
            myEntry.setContent(new PlainTextConstruct(memo));
            // 作成アプリ名
            Person author = new Person("TsuboneSystem", null, GOOGLE_ACCOUNT);

            myEntry.getAuthors().add(author);

            DateTime startTimeset = new DateTime();
            startTimeset.setTzShift(9);
            startTimeset = DateTime.parseDateTime("2014-07-17T08:00:00");;

            DateTime endTimeset = new DateTime();
            endTimeset.setTzShift(9);
            endTimeset = DateTime.parseDateTime("2014-07-17T17:00:00");

            // 開始終了日時をWhen型オブジェクトに代入し、イベントクラスに追加
            When eventTimes = new When();
            eventTimes.setStartTime(startTime);
            eventTimes.setEndTime(endTime);
            myEntry.addTime(eventTimes);

            // 場所をWhere型オブジェクトに代入し、イベントクラスに追加
            Where evLocation = new Where();
            evLocation.setValueString(place);
            myEntry.addLocation(evLocation);

            // Google Calendarサービスに接続
            CalendarService calService = new CalendarService("sample");
            calService.setUserCredentials(GOOGLE_ACCOUNT, GOOGLE_PASSWORD);

            // スケジュールを追加する
            CalendarEventEntry insertEntry = calService.insert(postURL, myEntry);

            // 特定のスケジュールを操作するリクエストを取得
            URL entryUrl = new URL(insertEntry.getSelfLink().getHref());
            EventEntry retrievedEntry = calService.getEntry(entryUrl, EventEntry.class);

            // 特定のスケジュールを探す
            Query myQuery = new Query(postURL);
            myQuery.setFullTextQuery(title);
            Feed myResultsFeed = calService.query(myQuery, Feed.class);
            if (myResultsFeed.getEntries().size() > 0) {
                Entry firstMatchEntry = myResultsFeed.getEntries().get(0);
                System.out.println("Titie: " + firstMatchEntry.getTitle().getPlainText());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}