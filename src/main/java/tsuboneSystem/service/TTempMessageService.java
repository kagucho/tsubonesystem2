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

import static org.seasar.extension.jdbc.operation.Operations.asc;
import static tsuboneSystem.names.TTempMessageNames.deleteFlag;
import static tsuboneSystem.names.TTempMessageNames.id;
import static tsuboneSystem.names.TTempMessageNames.targetMemberId;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TTempMessage;

/**
 * {@link TTempMessage}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/07/15 13:31:01")
public class TTempMessageService extends AbstractService<TTempMessage> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TTempMessage findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TTempMessage> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * memberIDをターゲットとしているメッセージを検索
     * @param memberId
     * @return
     */
    public List<TTempMessage> findByMemberId(Integer memberId) {
    	return select().where(new SimpleWhere().eq(targetMemberId(), memberId).eq(deleteFlag(), false)).getResultList();
    }
}