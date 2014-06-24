package tsuboneSystem.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;
import static tsuboneSystem.names.TPartyClubNames.*;
/**
 * {@link TPartyClub}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/06/05 23:52:00")
public class TPartyClubTest extends S2TestCase {

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
        jdbcManager.from(TPartyClub.class).id(1).getSingleResult();
    }

    /**
     * tPartyとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tParty() throws Exception {
        jdbcManager.from(TPartyClub.class).leftOuterJoin(tParty()).id(1).getSingleResult();
    }

    /**
     * tClubとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tMember() throws Exception {
        jdbcManager.from(TPartyClub.class).leftOuterJoin(tClub()).id(1).getSingleResult();
    }
}