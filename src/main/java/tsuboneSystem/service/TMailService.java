package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.original.manager.MailManager;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TMailNames.*;

/**
 * {@link TMail}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/05/01 18:25:13")
public class TMailService extends AbstractService<TMail> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TMail findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TMail> findAllOrderById() {
        return select().orderBy(desc(id())).limit(20).getResultList();
    }
    
    /**
     * @deprecated このメソッドは使用する必要がありません
     * {@link MailManager#setLogFlg(boolean, Integer)} の第一引数をTRUEにして自動的にログが残るようにしてください。
     */
    @Override
    public int insert(TMail entity) {
    	return super.insert(entity);
    }
}