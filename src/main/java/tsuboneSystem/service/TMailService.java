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

import static org.seasar.extension.jdbc.operation.Operations.desc;
import static tsuboneSystem.names.TMailNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.original.manager.MailManager;

/**
 * {@link TMail}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/05/01 18:25:13")
public class TMailService extends AbstractService<TMail> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TMail findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TMail> findAllOrderById() {
        return select().orderBy(desc(id())).getResultList();
    }
    
    /**
     * 識別子の昇順でlimitとoffsetの範囲でデータを検索します。
     * @param limit
     * @param offset
     * @return
     */
    public List<TMail> findAllOrderByIdLimitOffset(Integer browsingRights, int limit, int offset) {
    	
    	//引数(browsingRights)より大きい値を検索する
    	SimpleWhere where = new SimpleWhere();
    	where.ge(browsingRights(), browsingRights);
    	
    	return select().where(where).leftOuterJoin(tMember()).orderBy(desc(id())).limit(limit).offset(offset).getResultList();
    }
    
    /**
     * @deprecated このメソッドは使用する必要がありません
     * {@link MailManager#setLogFlg(boolean, Integer)} の第一引数をTRUEにして自動的にログが残るようにしてください。
     */
    @Override
    public int insert(TMail entity) {
    	return super.insert(entity);
    }
    
    /**
     * メール登録者で検索する
     * limitが-1なら制限を行わない。offsetも無視したい場合はoffsetを0にしてください。
     * @param member
     * @param limit
     * @param offset
     * @return
     */
    public List<TMail> findbyCreateId(int member, int limit, int offset) {
    	AutoSelect<TMail> query = select().where(new SimpleWhere().eq(registMemberId(), member)).orderBy(desc(id()));
    	if (limit != -1) {
    		query.limit(limit);
    	}
    	query.offset(offset);
    	 return query.getResultList();
    }
}