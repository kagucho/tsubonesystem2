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
import tsuboneSystem.entity.TPartyQuestion;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TPartyQuestionNames.*;

/**
 * {@link TPartyQuestion}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/30 7:32:36")
public class TPartyQuestionService extends AbstractService<TPartyQuestion> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyQuestion findById(Integer id) {
        return select().id(id)
        		.innerJoin("TMember")
        		.leftOuterJoin("tPartyAnswerList")
        		.leftOuterJoin("tPartyAnswerList.tMember")
        		.innerJoin("TParty")
        		.innerJoin("TParty.tPartyAttendList")
        		.innerJoin("TParty.tPartyAttendList.tMember").getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartyQuestion> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}