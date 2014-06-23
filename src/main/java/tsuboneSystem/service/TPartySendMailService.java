package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TPartySendMail;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TPartySendMailNames.*;

/**
 * {@link TPartySendMail}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/22 15:56:37")
public class TPartySendMailService extends AbstractService<TPartySendMail> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TPartySendMail findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TPartySendMail> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}