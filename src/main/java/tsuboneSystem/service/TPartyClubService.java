package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TPartyClub;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TPartyClubNames.*;

/**
 * {@link TPartyClub}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/05 23:51:57")
public class TPartyClubService extends AbstractService<TPartyClub> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyClub findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartyClub> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * PartyIdでエンティティを検索し、出席対象が部によって縛られていた場合、部に紐づくメンバーも取得します。
     * 
     * @param PartyId
     * 
     * @return エンティティのリスト
     */
    public List<TPartyClub> findByPartyId(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(PartyId(), partyId);
        return select()
        		.innerJoin("tClub",new SimpleWhere().eq("tClub.deleteFlag",  Boolean.valueOf(false)))
        		.innerJoin("tClub.tMemberClubList")
        		.innerJoin("tClub.tMemberClubList.tMember",
        				new SimpleWhere().eq("tClub.tMemberClubList.tMember.deleteFlag", Boolean.valueOf(false)))
        		.where(where).getResultList();
    }
    
    /**
     * PartyIdでエンティティを検索し、出席対象が部によって縛られていた場合、部に紐づくメンバー(OB含まず)も取得します。
     * 
     * @param PartyId
     * 
     * @return エンティティのリスト
     */
    public List<TPartyClub> findByPartyIdNoOB(Integer partyId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(PartyId(), partyId);
        return select()
        		.innerJoin("tClub",new SimpleWhere().eq("tClub.deleteFlag",  Boolean.valueOf(false)))
        		.innerJoin("tClub.tMemberClubList")
        		.innerJoin("tClub.tMemberClubList.tMember",
        				new SimpleWhere()
        					.eq("tClub.tMemberClubList.tMember.deleteFlag", Boolean.valueOf(false))
        					.eq("tClub.tMemberClubList.tMember.obFlag", Boolean.valueOf(false))
        				)
        		.where(where).getResultList();
    }
}