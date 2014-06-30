package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TPartyAnswer;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TPartyAnswerNames.*;

/**
 * {@link TPartyAnswer}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/30 15:15:48")
public class TPartyAnswerService extends AbstractService<TPartyAnswer> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartyAnswer findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartyAnswer> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}