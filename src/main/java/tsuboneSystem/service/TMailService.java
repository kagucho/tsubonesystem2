package tsuboneSystem.service;

import static org.seasar.extension.jdbc.operation.Operations.desc;
import static tsuboneSystem.names.TMailNames.id;
import static tsuboneSystem.names.TMailNames.registMemberId;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.original.manager.MailManager;

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
        return select().orderBy(desc(id())).getResultList();
    }
    
    /**
     * 識別子の昇順でlimitとoffsetの範囲でデータを検索します。
     * @param limit
     * @param offset
     * @return
     */
    public List<TMail> findAllOrderByIdLimitOffset(int limit, int offset) {
    	return select().orderBy(desc(id())).limit(limit).offset(offset).getResultList();
    }
    
    /**
     * @deprecated このメソッドは使用する必要がありません
     * {@link MailManager#setLogFlg(boolean, Integer)} の第一引数をTRUEにして自動的にログが残るようにしてください。
     */
    @Override
    public int insert(TMail entity) {
    	return super.insert(entity);
    }
    
    /**
     * メール登録者で検索する
     * limitが-1なら制限を行わない。offsetも無視したい場合はoffsetを0にしてください。
     * @param member
     * @param limit
     * @param offset
     * @return
     */
    public List<TMail> findbyCreateId(int member, int limit, int offset) {
    	AutoSelect<TMail> query = select().where(new SimpleWhere().eq(registMemberId(), member)).orderBy(desc(id()));
    	if (limit != -1) {
    		query.limit(limit);
    	}
    	query.offset(offset);
    	 return query.getResultList();
    }
}