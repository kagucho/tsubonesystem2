package tsuboneSystem.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TImageUpload;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TImageUploadNames.*;

/**
 * {@link TImageUpload}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/28 2:21:16")
public class TImageUploadService extends AbstractService<TImageUpload> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TImageUpload findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TImageUpload> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    
    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TImageUpload> findByImageFilePurposeCode(String imageFilePurposeCode) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(ImageFilePurpose(), imageFilePurposeCode);
        return select().where(where).orderBy(asc(id())).getResultList();
    }
}