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

import java.util.Date;
import java.util.HashMap;
import java.util.List;






import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.ComplexWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TTopAnnounce;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TTopAnnounceNames.*;

/**
 * {@link TTopAnnounce}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/13 16:43:00")
public class TTopAnnounceService extends AbstractService<TTopAnnounce> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TTopAnnounce findById(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(id(), id);
    	where.eq(deleteFlag(), false);
        return select().where(where)
        		.innerJoin(tMember())
        		.leftOuterJoin(tSubmitList())
        		.leftOuterJoin(tSubmitList().tMember())
//        		.leftOuterJoin(tSubmitList().tImageUpload())
        		.getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TTopAnnounce> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * 全ての一覧をかえします。
     * 
     * @return エンティティのリスト
     */
    public List<TTopAnnounce> findAll() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), false);
        return select().orderBy(desc(id())).where(where).innerJoin(tMember()).getResultList();
    }
    
    /**
     * 実行日に掲示されるお知らせのみをかえす
     * 
     * @return エンティティのリスト
     */
    public List<TTopAnnounce> checkDateList() {
    	Date date = new Date();
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), false);
    	where.and(where
    			// 掲載期間未設定
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.eq(deleteFlag(), false)
    			.or()
    			// 掲載開始日＜今日＜掲載終了日
    			.le(announceFromDay(), date)
    			.ge(announceToDay(), date)
    			.eq(deleteFlag(), false)
    			.or()
    			// 今日＜掲載終了日
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.ge(announceToDay(), date)
    			.eq(deleteFlag(), false)
    			.or()
    			// 掲載終了日＜今日
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.le(announceFromDay(), date)
    			.eq(deleteFlag(), false));
        return select().orderBy(desc(id())).where(where).innerJoin(tMember()).leftOuterJoin(tSubmitList()).getResultList();
    }
    
    /**
     * インサート共通処理
     * 
     * @return int
     */
    public int insertCustom (TTopAnnounce tTopAnnounce) {
    	tTopAnnounce.deleteFlag = false;
    	return super.insert(tTopAnnounce);
    }
    
    /**
     * おしらせの選択肢
     * 
     * @return Map<id, announceTitle>
     */
    public Map<String, String> getTopAnnounceMap () {
    	
    	Map<String, String> rtnMap = new HashMap<String, String>();
    	
    	// 選択肢にできるのは掲載期間が過ぎていない物であればよい
    	Date date = new Date();
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), false);
    	where.and(where
    			// 掲載期間未設定
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.eq(deleteFlag(), false)
    			.or()
    			// 今日＜掲載終了日
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.ge(announceToDay(), date)
    			.eq(deleteFlag(), false)
    			.or()
    			// 掲載終了日＜今日
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.le(announceFromDay(), date)
    			.eq(deleteFlag(), false));
    	List<TTopAnnounce> list = select().orderBy(desc(id())).where(where).innerJoin(tMember()).getResultList();
    	for (TTopAnnounce tTopAnnounce : list) {
    		rtnMap.put(tTopAnnounce.id.toString(), tTopAnnounce.announceTitle);
    	}
    	return rtnMap;
    }
    
    /**
     * おしらせの選択肢
     * 
     * @return Map<id, announceTitle>
     */
    public Map<Integer, String> getTopAnnounceMapIS() {
    	Map<Integer, String> rtnMap = new HashMap<Integer, String>();
    	// 選択肢にできるのは掲載期間が過ぎていない物であればよい
    	Date date = new Date();
    	ComplexWhere where = new ComplexWhere();
    	where.eq(deleteFlag(), false);
    	where.and(where
    			// 掲載期間未設定
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.eq(deleteFlag(), false)
    			.or()
    			// 今日＜掲載終了日
    			.isNull(announceFromDay(), Boolean.valueOf(true))
    			.ge(announceToDay(), date)
    			.eq(deleteFlag(), false)
    			.or()
    			// 掲載終了日＜今日
    			.isNull(announceToDay(),Boolean.valueOf(true))
    			.le(announceFromDay(), date)
    			.eq(deleteFlag(), false));
    	List<TTopAnnounce> list = select().orderBy(desc(id())).where(where).innerJoin(tMember()).getResultList();
    	for (TTopAnnounce tTopAnnounce : list) {
    		rtnMap.put(tTopAnnounce.id, tTopAnnounce.announceTitle);
    	}
    	return rtnMap;
    }
}