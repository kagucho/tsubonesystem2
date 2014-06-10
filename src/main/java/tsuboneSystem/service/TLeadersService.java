package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TLeaders;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TLeadersNames.*;

/**
 * {@link TLeaders}のサービスクラスです。
 * @author Hiroaki
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/12 14:20:28")
public class TLeadersService extends AbstractService<TLeaders> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TLeaders findById(Integer id) {
        return select()
        		.innerJoin("TMember")
        		.id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TLeaders> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * officerKindでエンティティを検索します。("1"は局長、"2"副局長)
     * 
     * @param kind
     *            識別子
     * @return エンティティ
     */
    public List<TLeaders> findByKind(String OfficerKind) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(OfficerKind(), OfficerKind);
        return select()
        		.innerJoin("TMember")
        		.where(where)
        		.getResultList();
    }
    
    /**
     * MemberIdでエンティティを検索します。
     * 
     * @param MemerId
     * @return エンティティ
     */
    public TLeaders findByMemberId(String id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), id);
        return select().where(where).getSingleResult();
    }
    
    /**
     * MemberIdでエンティティを検索します。
     * 
     * @param MemerId
     * @return エンティティのリスト
     */
    public List<TLeaders> findByMemberIdList(String id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), id);
        return select()
        		.innerJoin("TClub",new SimpleWhere().eq("TClub.deleteFlag",  Boolean.valueOf(false)))
        		.where(where)
        		.getResultList();
    }
}