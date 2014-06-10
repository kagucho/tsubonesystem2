package tsuboneSystem.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;
import tsuboneSystem.entity.TParty;
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
        return select().leftOuterJoin("tPartyClubList").id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findAllOrderById() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).orderBy(desc(meetingDeadlineDay())).getResultList();
    }
    
    /**
     * 実行された日時より遅い締め切りですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_Deadline_GE_Now(Date dateNow) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.ge(meetingDeadlineDay(), dateNow);
        return select()
        		.innerJoin("TMember")
        		.leftOuterJoin("tPartyClubList")
        		.where(where).orderBy(desc(id())).getResultList();
    }
    
    /**
     * 実行された日時より過ぎた締め切りですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_Deadline_LE_Now(Date dateNow) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.le(meetingDeadlineDay(), dateNow);
        return select().innerJoin("TMember").where(where).orderBy(desc(id())).getResultList();
    }
    
    /**
     * 実行された日時に等しい開催日のエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TParty> findBy_MeetingDay_EQ_Now(Date dateNow) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.eq(meetingDay(), dateNow);
        return select().innerJoin("TMember").where(where).orderBy(desc(id())).getResultList();
    }
}