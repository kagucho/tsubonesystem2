package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TPartySettings;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TPartySettingsNames.*;

/**
 * {@link TPartySettings}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/09/23 20:22:18")
public class TPartySettingsService extends AbstractService<TPartySettings> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartySettings findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartySettings> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}