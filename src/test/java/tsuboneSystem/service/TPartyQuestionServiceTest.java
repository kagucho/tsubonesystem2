package tsuboneSystem.service;

import javax.annotation.Generated;
import org.seasar.extension.unit.S2TestCase;

/**
 * {@link TPartyQuestionService}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceTestModelFactoryImpl"}, date = "2014/07/10 13:17:35")
public class TPartyQuestionServiceTest extends S2TestCase {

    private TPartyQuestionService tPartyQuestionService;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("app.dicon");
    }

    /**
     * {@link #tPartyQuestionService}が利用可能であることをテストします。
     * 
     * @throws Exception
     */
    public void testAvailable() throws Exception {
        assertNotNull(tPartyQuestionService);
    }
}