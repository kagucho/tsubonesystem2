package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TPartyAttend;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TMemberClubNames.MemberId;
import static tsuboneSystem.names.TPartyAttendNames.*;

/**
 * {@link TPartyAttend}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/13 15:02:32")
public class TPartyAttendService extends AbstractService<TPartyAttend> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyAttend findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartyAttend> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * PartyIdとMemberIdですべてのエンティティを検索します。
     * 
     * @return エンティティ
     */
	public TPartyAttend findByPartyIdMemberId(Integer partyId,Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(memberId(),memberId);
        return select().where(where).getSingleResult();
    }
    
    /**
     * PartyIdですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyId(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
        return select().where(where).getResultList();
    }
    
    /**
     * PartyIdですべてのエンティティを検索します。(出席)
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyIdAndAttendOn(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(attend(), Boolean.valueOf(true));
        return select().where(where).getResultList();
    }
    
    /**
     * PartyIdですべてのエンティティを検索します。(欠席)
     * 
     * @return エンティティのリスト
     */
	public List<TPartyAttend> findByPartyIdAndAttendOff(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(partyId(),partyId);
    	where.eq(attend(), Boolean.valueOf(false));
        return select().where(where).getResultList();
    }
    
    /**
     * MemberIdとPartyIdでエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyAttend findByMemberIdWithPartyId(String OffMemberId, Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), OffMemberId);
    	where.eq(partyId(), partyId);
        return select().where(where).getSingleResult();
    }
    
    
    
}