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

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TEnqueteAnswerNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TEnqueteAnswer;

/**
 * {@link TEnqueteAnswer}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/03 20:26:19")
public class TEnqueteAnswerService extends AbstractService<TEnqueteAnswer> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public TEnqueteAnswer findById(Integer id) {
		return select().id(id).getSingleResult();
	}

	/**
	 * アンケートのエンティティを検索します。
	 *
	 * @param enqueteSelectId
	 * @param userId
	 * @return アンケート
	 */
	public TEnqueteAnswer findByIdAndUserId(Integer enqueteId, Integer userId){
		SimpleWhere where = new SimpleWhere();
		where.eq(memberId(), userId);
		return select()
				.innerJoin(tEnqueteSelect(), new SimpleWhere().eq(tEnqueteSelect().enqueteId(), enqueteId))
				.where(where).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<TEnqueteAnswer> findAllOrderById() {
		return select().orderBy(asc(id())).getResultList();
	}

	/**
	 * 選択肢を選んだ人の数
	 *
	 * @return 選択肢を選んだ人の数
	 */
	public String resultNumCount(Integer enqueteSelectId) {
		SimpleWhere where = new SimpleWhere();
		where.eq(enqueteSelectedId(), enqueteSelectId);

		long count = jdbcManager.from(TEnqueteAnswer.class).where(where).getCount();

		return Long.toString(count);
	}

}