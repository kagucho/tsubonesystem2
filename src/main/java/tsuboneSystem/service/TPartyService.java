package tsuboneSystem.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.ComplexWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.SingletonS2Container;

import tsuboneSystem.code.PartyAttendCode;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.entity.TPartySettings;
import tsuboneSystem.form.PartyForm;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TPartyNames.*;

/**
 * {@link TParty}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/10 20:09:42")
public class TPartyService extends AbstractService<TParty> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TParty findById(Integer id) {
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyClubList())
        		.leftOuterJoin(tPartyQuestionList())
        		.leftOuterJoin(tPartyQuestionList().tMember())
        		.id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findAllOrderById() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where)
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.orderBy(desc(meetingDeadlineDay())).getResultList();
    }
    
    /**
     * 実行された日時より未来ですべてのエンティティを検索します。
     * @param memberId 
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_Deadline_GE_Now(Date dateNow, Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.ge(meetingDeadlineDay(), dateNow);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyClubList())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id())).getResultList();
    }
    
    /**
     * まだ締め切り前でかつ,引数の人がが出欠席を提出していないTPartyを取得する
     * @param dateNow
     * @param memberId
     * @return
     */
    public List<TParty> findNotAttendPartyByMemberId(Date dateNow, Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.ge(meetingDeadlineDay(), dateNow);
    	return select()
    			.innerJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId).eq(tPartyAttendList().attend(), PartyAttendCode.UNSUBMITTED.getCode()))
    			.innerJoin(tPartySettings())
    			.where(where)
    			.getResultList();
    			
    }
    
    /**
     * 実行された日が、指定された日数分(beforDay)締め切り日より前である会議を検索する。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_Deadline_PULS(Date dateNow, int beforDay, boolean necessaryFlag) {
    	
    	//現在時刻に任意の日にち分足す
    	GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(dateNow);
		calendar.add(Calendar.DATE, beforDay);
		Date dateadd = new Date();
		dateadd=calendar.getTime();
		
		//検索 
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.eq(meetingNecessaryFlag(), Boolean.valueOf(necessaryFlag));
    	where.eq(meetingDeadlineDay(), dateadd);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyClubList())
        		.where(where).orderBy(desc(id())).getResultList();
    }
    
    /**
     * 実行された日が、指定された日数分(beforDay)締め切り日より前(between)である会議を検索する。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_Deadline_PULS_BETWEEN(Date dateNow, int beforDay, boolean necessaryFlag) {
    	
    	//現在時刻に任意の日にち分足す
    	GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(dateNow);
		calendar.add(Calendar.DATE, beforDay);
		Date dateadd = new Date();
		dateadd=calendar.getTime();
		
		//検索 
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.eq(meetingNecessaryFlag(), Boolean.valueOf(necessaryFlag));
    	where.le(meetingDeadlineDay(), dateadd);
    	where.ge(meetingDeadlineDay(), dateNow);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyClubList())
        		.where(where).orderBy(desc(id())).getResultList();
    }
    
    /**
     * 実行された日時より過ぎた締め切りですべてのエンティティを検索します。
     * @param memberId 
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_Deadline_LE_Now(Date dateNow, Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.le(meetingDeadlineDay(), dateNow);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id()))
        		.getResultList();
    }
    
    /**
     * 実行された日時に開催されている会議のエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_MeetingDay_BETWEEN_Now(Date dateNow, Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.le(meetingDay(), dateNow);
    	where.ge(meetingEndDay(), dateNow);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id())).getResultList();
    }
    
    /**
     * 実行された日時に対して一ヶ月より前に作成され、開催日が設定されなかった会議(登録されてから一ヶ月たった)のエンティティを検索します。
     * @param memberId 
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_NODeadline_LE(Date dateNow, Integer memberId) {
    	
    	//現在時刻に任意の日にち分足す
    	GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(dateNow);
		calendar.add(Calendar.MONTH, -1);
		Date dateadd = new Date();
		dateadd = calendar.getTime();
		
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.isNull(meetingDeadlineDay(), Boolean.valueOf(true));
    	where.isNull(meetingDay(), Boolean.valueOf(true));
    	where.le(updateTime(), dateadd);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id()))
        		.getResultList();
    }
    
    /**
     * 実行された日時に対して終了した会議のエンティティを検索します。
     * @param memberId 
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findByOldParty(Date dateNow, Integer memberId){
    	
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.and(where
    			//(開催日 < 今) かつ (締め切り < 今)締め切りは過ぎているがまだ開催しない
    			.lt(meetingDeadlineDay(), dateNow)
    			.lt(meetingDay(), dateNow)
    			.or()
    			//(開催日 < 今) かつ (締め切り = なし)
    			.isNull(meetingDeadlineDay(), Boolean.valueOf(true))
    			.lt(meetingDay(), dateNow)
    			.or()
    			//(開催日 = なし) かつ (締め切り < 今)
    			.isNull(meetingDay(),Boolean.valueOf(true))
    			.lt(meetingDeadlineDay(), dateNow));
    	
    	return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id()))
        		.getResultList();
    }
    
    /**
     * 実行された日時に対して一ヶ月以内に作成され、締め切り日が設定されない会議のエンティティを検索します。
     * 
     * @param date(現在日時), memberId(ログインメンバーＩＤ), meetingDayFlag(開催日時が設定されているか)
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_NODeadline_GE(Date dateNow, Integer memberId, boolean meetingDayFlag) {
    	
    	//現在時刻に任意の日にち分足す
    	GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(dateNow);
		calendar.add(Calendar.MONTH, -1);
		Date dateadd = new Date();
		dateadd=calendar.getTime();
		
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.isNull(meetingDeadlineDay(), Boolean.valueOf(true));
    	where.and(getComplex(dateadd, dateNow, meetingDayFlag));
    	
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id()))
        		.getResultList();
    }
    
    /**
     * 実行された日時に対して一ヶ月以内に作成され、開催日が設定されなかった会議のエンティティを検索します。
     * @param memberId 
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_NO_MeetingDay_GE(Date dateNow, Integer memberId) {
    	
    	//現在時刻に任意の日にち分足す
    	GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(dateNow);
		calendar.add(Calendar.MONTH, -1);
		Date dateadd = new Date();
		dateadd=calendar.getTime();
		
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.isNull(meetingDay(), Boolean.valueOf(true));
    	where.ge(updateTime(), dateadd);
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id()))
        		.getResultList();
    }
    
    /**
     * 実行された日時に対して一ヶ月以内に作成され、開催日が設定されなかった会議のエンティティを検索します。
     * @param memberId 
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_WillMeeting(Date dateNow, Integer memberId) {
		
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.and(where
    			//(開催日 > 今) かつ (締め切り < 今)締め切りは過ぎているがまだ開催しない
    			.lt(meetingDeadlineDay(), dateNow)
    			.gt(meetingDay(), dateNow)
    			.or()
    			//(開催日 >= 今) かつ (締め切り = なし)
    			.isNull(meetingDeadlineDay(), Boolean.valueOf(true))
    			.gt(meetingDay(), dateNow)
    			.or()
    			//(開催日 = なし) かつ (締め切り < 今)
    			.isNull(meetingDay(),Boolean.valueOf(true))
    			.le(meetingDeadlineDay(), dateNow));
//    	where.and(where
//    			.le(meetingDay(), dateNow));
        return select()
        		.innerJoin(tMember())
        		.innerJoin(tPartySettings())
        		.leftOuterJoin(tPartyAttendList(), new SimpleWhere().eq(tPartyAttendList().memberId(), memberId))
        		.where(where).orderBy(desc(id()))
        		.getResultList();
    }
    
    
    public ComplexWhere getComplex(Date dateadd, Date dateNow, boolean meetingDayFlag){
    	
    	ComplexWhere whereComp = new ComplexWhere();
    	if(meetingDayFlag){
    		whereComp.isNull(meetingDay(), Boolean.valueOf(true));
    		whereComp.ge(updateTime(), dateadd);
    	}else{
    		whereComp.ge(meetingDay(), dateNow)
    		.isNull(meetingEndDay(), true)
    		.or()
    		.ge(meetingEndDay(), dateNow);
    	}
    	
    	return whereComp;
    }
    
    /**
     * 基礎イベント情報と付随イベント情報を同時に登録します
     * @param PartyForm 
     * 
     * @return エンティティ
     */
    public TParty insertCustom (Integer registId, PartyForm partyForm) {
    	
    	//基礎イベント情報登録
    	TParty tParty = new TParty(registId, partyForm);
    	super.insert(tParty);
    	
    	//付随イベント情報登録
    	TPartySettings tPartySettings = new TPartySettings();
    	Beans.copy(partyForm, tPartySettings).execute();
    	tPartySettings.partyId = tParty.id;
    	TPartySettingsService tPartySettingsService = SingletonS2Container.getComponent(TPartySettingsService.class);
    	tPartySettingsService.insert(tPartySettings);
    	
    	return tParty;
    }
    
}