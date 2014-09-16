package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TEnqueteSelect;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TEnqueteSelectNames.*;

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
}