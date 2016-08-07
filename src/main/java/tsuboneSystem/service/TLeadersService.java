/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
        		.innerJoin(tMember())
        		.id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TLeaders> findAllOrderById() {
        return select().innerJoin(tMember()).orderBy(asc(id())).getResultList();
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
        		.innerJoin(tMember())
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
     * MemberIdでエンティティを検索し、現役の部長のリストで返す。
     * 
     * @param MemerId
     * @return エンティティのリスト
     */
    public List<TLeaders> findByMemberIdList(Integer memberId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), memberId);
        return select().where(where)
        		.innerJoin(tClub(),new SimpleWhere().eq(tClub().deleteFlag(),  Boolean.valueOf(false)))
        		.getResultList();
    }
    
    /**
     * MemberIdでエンティティを検索します。(orderByOfficerKind)
     * 
     * @param MemerId
     * @return エンティティのリスト
     */
    public List<TLeaders> findByMemberIdOrderKindList(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(MemberId(), id);
        return select()
        		.leftOuterJoin(tClub(),new SimpleWhere().eq(tClub().deleteFlag(),  Boolean.valueOf(false)))
        		.where(where)
        		.orderBy(asc(OfficerKind()))
        		.getResultList();
    }
    
    /**
     * MemberId、officerKindでエンティティを検索します。
     * 
     * @param MemerId
     * @return エンティティのリスト
     */
    public List<TLeaders> findByMemberId_OfficerKindList(String id,String officerKind) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(OfficerKind(), officerKind);
    	where.eq(MemberId(), id);
        return select()
        		.leftOuterJoin(tClub(),new SimpleWhere().eq(tClub().deleteFlag(),  Boolean.valueOf(false)))
        		.where(where)
        		.getResultList();
    }
}