package tsuboneSystem.service;

import java.util.Date;
import java.util.List;





import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.ComplexWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TTopAnnounce;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TTopAnnounceNames.*;

/**
 * {@link TTopAnnounce}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/13 16:43:00")
public class TTopAnnounceService extends AbstractService<TTopAnnounce> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TTopAnnounce findById(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(id(), id);
    	where.eq(deleteFlag(), false);
        return select().where(where).innerJoin(tMember()).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TTopAnnounce> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * 全ての一覧をかえします。
     * 
     * @return エンティティのリスト
     */
    public List<TTopAnnounce> findAll() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), false);
        return select().orderBy(desc(id())).where(where).innerJoin(tMember()).getResultList();
    }
    
    /**
     * 実行日に掲示されるお知らせのみをかえす
     * 
     * @return エンティティのリスト
     */
    public List<TTopAnnounce> checkDateList() {
    	Date date = new Date();
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), false);
    	where.and(where
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.eq(deleteFlag(), false)
    			.or()
    			// 掲載開始日＜今日＜掲載終了日
    			.le(announceFromDay(), date)
    			.ge(announceToDay(), date)
    			.eq(deleteFlag(), false)
    			.or()
    			// 今日＜掲載終了日
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.ge(announceToDay(), date)
    			.eq(deleteFlag(), false)
    			.or()
    			// 掲載終了日＜きょう
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.le(announceFromDay(), date)
    			.eq(deleteFlag(), false));
        return select().orderBy(desc(id())).where(where).innerJoin(tMember()).getResultList();
    }
    
    /**
     * インサート共通処理
     * 
     * @return int
     */
    public int insertCustom (TTopAnnounce tTopAnnounce) {
    	tTopAnnounce.deleteFlag = false;
    	return super.insert(tTopAnnounce);
    }
}