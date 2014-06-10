package tsuboneSystem.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static tsuboneSystem.names.TClubNames.*;

/**
 * {@link TClub}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/04/07 17:47:04")
public class TClubTest extends S2TestCase {

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
        jdbcManager.from(TClub.class).id(1).getSingleResult();
    }

    /**
     * tMemberClubListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tMemberClubList() throws Exception {
        jdbcManager.from(TClub.class).leftOuterJoin(tMemberClubList()).id(1).getSingleResult();
    }
}