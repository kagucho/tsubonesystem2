package tsuboneSystem.entity;

import static tsuboneSystem.names.TEnqueteAnswerNames.*;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

/**
 * {@link TEnqueteAnswer}のテストクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/11/03 20:26:24")
public class TEnqueteAnswerTest extends S2TestCase {

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
        jdbcManager.from(TEnqueteAnswer.class).id(1).getSingleResult();
    }

    /**
     * tEnqueteSelectとの外部結合をテストします。
     *
     * @throws Exception
     */
    public void testLeftOuterJoin_tEnqueteSelect() throws Exception {
        jdbcManager.from(TEnqueteAnswer.class).leftOuterJoin(tEnqueteSelect()).id(1).getSingleResult();
    }

    /**
     * tMemberとの外部結合をテストします。
     *
     * @throws Exception
     */
    public void testLeftOuterJoin_tMember() throws Exception {
        jdbcManager.from(TEnqueteAnswer.class).leftOuterJoin(tMember()).id(1).getSingleResult();
    }
}