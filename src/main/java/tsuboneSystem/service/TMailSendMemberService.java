package tsuboneSystem.service;

import java.util.List;
import javax.annotation.Generated;
import tsuboneSystem.entity.TMailSendMember;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TMailSendMemberNames.*;

/**
 * {@link TMailSendMember}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/05/01 18:25:13")
public class TMailSendMemberService extends AbstractService<TMailSendMember> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TMailSendMember findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TMailSendMember> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}