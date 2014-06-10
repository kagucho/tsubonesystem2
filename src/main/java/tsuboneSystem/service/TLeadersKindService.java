package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TLeadersKind;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TLeadersKindNames.*;

/**
 * {@link TLeadersKind}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/05/17 12:02:39")
public class TLeadersKindService extends AbstractService<TLeadersKind> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TLeadersKind findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TLeadersKind> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}