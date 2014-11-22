package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TSubmit;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TSubmitNames.*;

/**
 * {@link TSubmit}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/20 21:41:29")
public class TSubmitService extends AbstractService<TSubmit> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TSubmit findById(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(id(), id);
    	where.eq(deleteFlag(), false);
        return select().where(where).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TSubmit> findAllOrderById() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), false);
        return select().where(where).innerJoin(tMember()).orderBy(asc(id())).getResultList();
    }
    
    /**
     * インサート共通処理
     * 
     * @return エンティティのリスト
     */
    public int insertCustom (TSubmit tSubmit) {
    	tSubmit.deleteFlag = false;
    	return super.insert(tSubmit);
    	}
}