package tsuboneSystem.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static tsuboneSystem.names.TMailSendMemberNames.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import tsuboneSystem.entity.TMail;
import tsuboneSystem.entity.TMailSendMember;
import tsuboneSystem.form.MailListForm;
import tsuboneSystem.original.manager.MailManager;

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

    /**
     * 自分に届いたメールのみ一覧で検索する
     *
     * @return エンティティのリスト
     */
    public List<TMail> findAllOrderByIdLimitOffset (MailListForm mailListForm, Integer loginMemberId, int browsingRights, int limit, int offset) {


    	//検索条件：自分に届いたメールフラグ
    	boolean myMailCheck = (mailListForm.myMailCheck != null);

    	SimpleWhere where = new SimpleWhere();
    	if (myMailCheck) {
    		where.eq(memberId(), loginMemberId);

    	}

    	//一覧を取得
    	List<TMailSendMember> list = select().where(where)
    			.innerJoin(tMail(), new SimpleWhere().ge(tMail().browsingRights(), browsingRights))
    			.limit(limit).offset(offset).getResultList();

    	//TMailの一覧に直すs
    	List<TMail> mailList = new ArrayList<TMail>();
    	for (TMailSendMember mailSendMember : list) {
    		mailList.add(mailSendMember.tMail);
    	}

    	return mailList;
    }



    /**
     * @deprecated このメソッドは使用する必要がありません
     * {@link MailManager#setLogFlg(boolean, Integer)} の第一引数をTRUEにして自動的にログが残るようにしてください。
     */
    @Override
    public int insert(TMailSendMember entity) {
    	return super.insert(entity);
    }
}