package tsuboneSystem.service;

import static org.seasar.extension.jdbc.operation.Operations.asc;
import static org.seasar.extension.jdbc.operation.Operations.desc;
import static tsuboneSystem.names.TClubNames.deleteFlag;
import static tsuboneSystem.names.TMemberNames.entrance;
import static tsuboneSystem.names.TMemberNames.hname;
import static tsuboneSystem.names.TMemberNames.id;
import static tsuboneSystem.names.TMemberNames.name;
import static tsuboneSystem.names.TMemberNames.obFlag;
import static tsuboneSystem.names.TMemberNames.password;
import static tsuboneSystem.names.TMemberNames.userName;

import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.ComplexWhere;
import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.original.util.DigestUtil;

/**
 * {@link TMember}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/07 18:14:16")
public class TMemberService extends AbstractService<TMember> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TMember findById(Integer id) {
        return select().id(id).getSingleResult();
    }
    
    /**
     * OB宣言していないメンバーのなかでID検索する
     * 
     * @return エンティティのリスト
     */
    @SuppressWarnings("boxing")
	public TMember findByIdNoOB(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(id(), id);
    	where.eq(obFlag(), false);
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).getSingleResult();
    }
    
    
    /**
     * OB宣言していないメンバーの一覧を返す
     * 
     * @return エンティティのリスト
     */
	public List<TMember> findByIdNoOBAll() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(obFlag(), Boolean.toString(false));
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).getResultList();
    }
	
	/**
     * メンバーの一覧を返す
     * 
     * @return エンティティのリスト
     */
	public List<TMember> findByIdAll() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).getResultList();
    }
    
    /**
     * 現役メンバーの一覧を入学年度順に返す
     * 
     * @return エンティティのリスト
     */
    public List<TMember> findByAllOrderEntrance() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(obFlag(), Boolean.toString(false));
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select()
        		.where(where)
        		.orderBy(asc(id()))
        		.orderBy(asc(hname()))
        		.orderBy(desc(entrance()))
        		.getResultList();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TMember> findAllOrderById() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).orderBy(asc(id())).getResultList();
    }
    
    /**
     * userNmaeですべてのエンティティを検索します。loginに使用
     * 
     * @return エンティティ
     */
    public TMember findByLoginIdPassword(String id, String password){
    	SimpleWhere where = new SimpleWhere();
    	where.eq(userName(), id);
    	where.eq(password(), password);
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	return select().where(where).getSingleResult();
    }
    
    /**
     * userNmaeですべてのエンティティを検索します。loginに使用
     * 
     * @return エンティティ
     */
    public TMember findByUserName(String UserName){
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.eq(userName(), UserName);
    	return select().where(where).getSingleResult();
    }
    
    /**
     * 検索条件ですべてのエンティティを検索します。(OB抜き)
     * 
     * @return エンティティのリスト
     */
    public List<TMember> findBySearch(String name,String hname, String entrance) {
    	//以下から文字をNULLに置き換える
    	if (name.isEmpty()) {
    		name = null;
    	}
    	if (hname.isEmpty()) {
    		hname = null;
    	}
    	if (entrance.isEmpty()) {
    		entrance = null;
    	}
    	ComplexWhere where = new ComplexWhere();
    	where.and(
    			new ComplexWhere()
	    			.contains(name(), name)
	    			.contains(hname(), hname)
	    			.eq(entrance(), entrance)
    			);
    	where.and(
    			new ComplexWhere()
    				.eq(deleteFlag(), Boolean.valueOf(false))
    				.eq(obFlag(), Boolean.toString(false))
    			);
    	return select().where(where).orderBy(asc(id())).getResultList();
    }
    
    /**
     * 検索条件ですべてのエンティティを検索します。(OBあり)
     * 
     * @return エンティティのリスト
     */
    public List<TMember> findBySearchOnOB(String name,String hname, String entrance) {
    	//OBを含めるチェックがある上で、その他の検索条件が入力されていない場合は、OBを含めた一覧を取得する
    	if (name.isEmpty() && hname.isEmpty() && entrance.isEmpty()) {
    		return select().orderBy(asc(id())).getResultList();
    	}
    	//以下から文字をNULLに置き換える
    	if (name.isEmpty()) {
    		name = null;
    	}
    	if (hname.isEmpty()) {
    		hname = null;
    	}
    	if (entrance.isEmpty()) {
    		entrance = null;
    	}
    	ComplexWhere where = new ComplexWhere();
    	where.and(
    			new ComplexWhere()
	    			.contains(name(), name)
	    			.contains(hname(), hname)
	    			.eq(entrance(), entrance)
    			);
    	where.and(
    			new ComplexWhere()
    				.eq(deleteFlag(), Boolean.valueOf(false))
    			);
    	return select().where(where).orderBy(asc(id())).getResultList();
    }

    /**
     * OB宣言していないメンバーのマップを返す
     * 
     * @return Map
     */
    public Map<String,String> getMemberMap(){
    	SimpleWhere where = new SimpleWhere();
    	where.eq(obFlag(),Character.valueOf('0'));
    	List<TMember> list = select().where(where).getResultList();
    	for (TMember member : list){
    	getMemberMap().put(member.id.toString(), member.hname);
    	}return getMemberMap();
    }
    
    @Override
    public int insert(TMember entity) {
    	//パスワードのハッシュ化
    	entity.password = DigestUtil.md5(entity.password);
    	return super.insert(entity);
    }
}