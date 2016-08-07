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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TSubmitTagKind;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TSubmitTagKindNames.*;

/**
 * {@link TSubmitTagKind}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/20 21:41:29")
public class TSubmitTagKindService extends AbstractService<TSubmitTagKind> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TSubmitTagKind findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TSubmitTagKind> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TSubmitTagKind> findAll() {
    	
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), false);
    	
        return select().where(where).getResultList();
    }
    
    /**
     * 作品の分類タグのマップを返す
     * 
     * @return Map<id, submitTagName>
     */
    public Map<String, String> getSubmitTagKindMap (){
    	Map<String, String> rtnMap = new HashMap<String, String>();
    	List<TSubmitTagKind> list = findAll();
    	for (TSubmitTagKind tSubmitTagKind : list) {
    		rtnMap.put(tSubmitTagKind.id.toString(), tSubmitTagKind.submitTagName);
    	}
    	return rtnMap;
    }
    
    /**
     * 作品の分類タグのマップを返す
     * 
     * @return Map<id, submitTagName>
     */
    public Map<Integer, String> getSubmitTagKindMapIS (){
    	Map<Integer, String> rtnMap = new HashMap<Integer, String>();
    	List<TSubmitTagKind> list = findAll();
    	for (TSubmitTagKind tSubmitTagKind : list) {
    		rtnMap.put(tSubmitTagKind.id, tSubmitTagKind.submitTagName);
    	}
    	return rtnMap;
    }
}