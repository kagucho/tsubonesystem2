package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TBbsSubject;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TBbsSubjectNames.*;

/**
 * {@link TBbsSubject}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/07/10 13:17:31")
public class TBbsSubjectService extends AbstractService<TBbsSubject> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TBbsSubject findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TBbsSubject> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * 最終編集時間の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TBbsSubject> findOrderByUpdateTime() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).innerJoin("tMember").orderBy(desc(updateTime())).getResultList();
    }
}