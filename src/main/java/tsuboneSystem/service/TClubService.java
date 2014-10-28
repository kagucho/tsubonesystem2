package tsuboneSystem.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.container.SingletonS2Container;

import tsuboneSystem.entity.TClub;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TClubNames.*;

/**
 * {@link TClub}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/04/07 18:14:16")
public class TClubService extends AbstractService<TClub> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TClub findById(Integer id) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(id(), id);
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select()
        		.where(where)
        		.innerJoin(tLeaders())
        		.innerJoin(tLeaders().tMember())
        		.getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TClub> findAllOrderById() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).orderBy(asc(id())).getResultList();
    }
    
    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TClub> findAllInTmember() {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select()
        		.where(where)
        		.innerJoin(tLeaders())
        		.innerJoin(tLeaders().tMember())
        		.getResultList();
    }
    
    /**
     * LeadersIdでエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public List<TClub> findByLeadersId(Integer LeadersId) {
    	SimpleWhere where = new SimpleWhere();
    	where.eq(LeadersId(), LeadersId);
    	where.eq(deleteFlag(), Boolean.valueOf(false));
        return select().where(where).getResultList();
    }
    
    /**
     * クラブのマップ<Integer, String>
     * @return Map
     */
    
    public HashMap<Integer, String> getClubMapIS(){
		
    	//戻り値を格納するmap
		HashMap<Integer, String> rtnMap = new HashMap<Integer, String>();
		
		//検索
		SimpleWhere where = new SimpleWhere();
		where.eq(deleteFlag(), Boolean.valueOf(false));
    	List<TClub> list = select().where(where).getResultList();
    	
    	//for文でリストのリストの情報を１つずつマップに入れ込んでいく
        for ( TClub tClub : list) {
        	//key(数値)はclubのidを(型をstringに変換)、valu(名称)はclubの名前
        	rtnMap.put(tClub.id, tClub.ClubName);
        }
		return rtnMap;
    }
    
    /**
	 * keyをclubId, valueをclubNameとしてマップを作成する
	 * @return
	 */
	public HashMap<String, String> getClubMap() {
		HashMap<String, String> rtnMap = new HashMap<String, String>();
		for ( TClub club : findAllOrderById()) {
        	rtnMap.put(club.id.toString(), club.ClubName);
        }
		return rtnMap;
	}
    
    public int deleteCustom (TClub tClub) {
    	//該当の部を論理削除
    	TClub tClubDelete = tClub;
    	tClubDelete.deleteFlag = Boolean.valueOf(true);
    	tClubDelete.LeadersId = null;
    	int i = super.update(tClubDelete);
    	//部長のレコードを削除
    	TLeadersService tLeadersService = SingletonS2Container.getComponent(TLeadersService.class);
    	tLeadersService.delete(tClub.tLeaders);
    	
    	return i;
    }
}