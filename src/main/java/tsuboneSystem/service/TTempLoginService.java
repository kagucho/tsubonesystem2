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

import tsuboneSystem.entity.TTempLogin;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TClubNames.deleteFlag;
import static tsuboneSystem.names.TTempLoginNames.*;

/**
 * {@link TTempLogin}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/08/23 17:03:12")
public class TTempLoginService extends AbstractService<TTempLogin> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TTempLogin findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TTempLogin> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    
    /**
     * userNmaeですべてのエンティティを検索します。loginに使用
     * 
     * @return エンティティ
     */
    public TTempLogin findByLoginIdPassword(String id, String password){
    	SimpleWhere where = new SimpleWhere();
    	where.eq(userName(), id);
    	where.eq(password(), password);
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	return select().where(where).getSingleResult();
    }
    
    /**
     * userNmaeですべてのエンティティを検索します。loginに使用
     * 
     * @return エンティティ
     */
    public TTempLogin findByUserName(String UserName){
    	SimpleWhere where = new SimpleWhere();
    	where.eq(userName(), UserName);
    	return select().where(where).getSingleResult();
    }
}