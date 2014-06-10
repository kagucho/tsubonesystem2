package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TClub;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TClubNames.*;

/**
 * {@link TClub}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/07 18:14:16")
public class TClubService extends AbstractService<TClub> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TClub findById(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().innerJoin("TLeaders").innerJoin("TLeaders.tMember").where(where).id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TClub> findAllOrderById() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).orderBy(asc(id())).getResultList();
    }
    
    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TClub> findAllInTmember() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select()
        		.where(where)
        		.innerJoin("TLeaders")
        		.innerJoin("TLeaders.tMember")
        		.getResultList();
    }
    
    /**
     * LeadersIdでエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TClub findByLeadersId(Integer LeadersId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(LeadersId(), LeadersId);
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).getSingleResult();
    }

}