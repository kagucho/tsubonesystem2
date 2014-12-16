package tsuboneSystem.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TEnqueteSelectNames.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TEnqueteSelect;

/**
 * {@link TEnqueteSelect}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/09/02 19:13:10")
public class TEnqueteSelectService extends AbstractService<TEnqueteSelect> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TEnqueteSelect findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<TEnqueteSelect> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }

    /**
     * 選択肢のリストを検索します。
     * @param enqueteId
     * 					識別子
     * @return	選択肢のリスト
     */
    public Map<String, String> enqueteSelectMap(Integer enqueteId) {

    	SimpleWhere where = new SimpleWhere();
    	where.eq(enqueteId(), enqueteId);

    	List<TEnqueteSelect> list = select().where(where).getResultList();

    	Map<String, String> map = new HashMap<String, String>();

    	for (TEnqueteSelect enqueteSelect : list) {
    		map.put(enqueteSelect.id.toString(), enqueteSelect.selectedContents);
    	}

    	return map;
    }


}