package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TAdmin;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TAdminNames.*;
import static tsuboneSystem.names.TLeadersNames.OfficerKind;

/**
 * {@link TAdmin}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/08/11 0:13:46")
public class TAdminService extends AbstractService<TAdmin> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TAdmin findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TAdmin> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * memberIdでエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TAdmin findByMemberId(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), id);
        return select().where(where).getSingleResult();
    }
    
    /**
     * memberIdでエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public List<TAdmin> findByMemberIdList(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), id);
        return select().where(where).getResultList();
    }
    
    /**
     * officerKindでエンティティを検索します。("1"は局長、"2"副局長)
     * 
     * @param kind
     *            識別子
     * @return エンティティ
     */
    public List<TAdmin> findByKind(String OfficerKind) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(OfficerKind(), OfficerKind);
        return select()
        		.innerJoin(tMember())
        		.where(where)
        		.getResultList();
    }
    
}