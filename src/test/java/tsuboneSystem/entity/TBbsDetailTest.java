package tsuboneSystem.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static tsuboneSystem.names.TBbsDetailNames.*;

/**
 * {@link TBbsDetail}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/07/10 13:17:33")
public class TBbsDetailTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(TBbsDetail.class).id(1).getSingleResult();
    }

    /**
     * tMemberとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tMember() throws Exception {
        jdbcManager.from(TBbsDetail.class).leftOuterJoin(tMember()).id(1).getSingleResult();
    }

    /**
     * tBbsSubjectとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tBbsSubject() throws Exception {
        jdbcManager.from(TBbsDetail.class).leftOuterJoin(tBbsSubject()).id(1).getSingleResult();
    }

    /**
     * tMailとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tMail() throws Exception {
        jdbcManager.from(TBbsDetail.class).leftOuterJoin(tMail()).id(1).getSingleResult();
    }
}