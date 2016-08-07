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
        return select().innerJoin(tMember()).id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TAdmin> findAllOrderById() {
        return select().innerJoin(tMember()).orderBy(asc(id())).getResultList();
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