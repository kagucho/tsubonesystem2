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

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TMember;
import tsuboneSystem.form.MemberListForm;
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
     * メンバーの一覧を返す
     * 
     * @return エンティティのリスト
     */
	public TMember findByEmail(String email) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	where.eq("mail", email);
        return select().where(where).getSingleResult();
    }
    
    /**
     * 現役メンバーの一覧を入学年度順に返す。引数のどちらかが-1の時は全件検索します
     * @param limit
     * @param offset
     * @return エンティティのリスト
     */
    public List<TMember> findByAllOrderEntrance(int limit, int offset) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(obFlag(), Boolean.toString(false));
    	where.eq(deleteFlag(), Boolean.valueOf(false));
    	AutoSelect<TMember> autoSelect = select()
    			.where(where)
    			.orderBy(asc(id()))
				.orderBy(asc(hname()))
				.orderBy(desc(entrance()));
    	if (limit == -1 || offset == -1) {
    		return autoSelect.getResultList();
    	} else {
    		return autoSelect.limit(limit).offset(offset).getResultList();
    	}
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
    
    public List<TMember> findByMailAddres(String[] mailAddress) {
    	SimpleWhere where = new SimpleWhere();
    	where.in("MAIL", (Object[])mailAddress);
		return select().where(where).getResultList();
    	
    }
    
    /**
     * 検索条件ですべてのエンティティを検索します。もしlimitとoffsetのどちらかが-1の場合は全てのデータを検索します。
     * @param memberListForm 検索条件格納
     * @return エンティティのリスト
     */
    public List<TMember> findBySearch(MemberListForm memberListForm, int limit, int offset) {
    	//検索条件：名前
    	String name = memberListForm.name;
    	//検索条件：ハンドルネーム
    	String hname = memberListForm.hname;
    	//検索条件：入学年度
    	String entrance = memberListForm.entrance;
    	//検索条件：OBフラグ
    	boolean containsOB = (memberListForm.obFlag != null);
    	
    	SimpleWhere where = new SimpleWhere();
    	//引数が空じゃなかったら検索条件に含める
    	if (!name.isEmpty()) {
    		where = where.contains(name(), name);
    	}
    	if (!hname.isEmpty()) {
    		where = where.contains(hname(), hname);
    	}
    	if (!entrance.isEmpty()) {
    		where = where.eq(entrance(), entrance);
    	}
    	
    	//OBを含めるかどうかの処理
    	if (!containsOB) {
    		where = where.eq(obFlag(), false);
    	}
    	
    	//削除済みを入れないのは共通処理
    	where = where.eq(deleteFlag(), Boolean.valueOf(false));
    	AutoSelect<TMember> autoSelect = select().where(where).orderBy(asc(id()));
    	if (limit == -1 || offset == -1) {
    		return autoSelect.getResultList();
    	} else {
    		return autoSelect.limit(limit).offset(offset).getResultList();
    	}
    }
    
    /**
     * OB宣言していないメンバーのマップを返す
     * @deprecated 無限ループします
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