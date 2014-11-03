package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TEnqueteAnswer;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TEnqueteAnswerNames.*;

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
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TEnqueteAnswer> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}