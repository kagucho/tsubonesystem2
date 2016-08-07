/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tsuboneSystem.entity;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.annotation.Generated;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.manager.JdbcManagerImplementor;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.util.InputStreamReaderUtil;
import org.seasar.framework.util.PreparedStatementUtil;
import org.seasar.framework.util.ReaderUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StatementUtil;

/**
 * SQLファイルのテストクラスです。
 * <p>
 * このファイルは修正されることを意図していません。
 * SQLファイルのテストを独自に行いたい場合は、サービスやエンティティのテストクラスを使用してください。
 * </p>
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.SqlFileTestModelFactoryImpl"}, date = "2015/03/21 13:09:07")
public class SqlFileTest extends S2TestCase {

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
     * SQLファイルがひとつもありません。
     * ダミーのテストメソッドです。
     * 
     * @throws Exception
     */
    public void test() throws Exception {
    }

    /**
     * SQLファイルを表すクラスです。
     * 
     * @author S2JDBC-Gen
     */
    public class SqlFile {

        /** SQL */
        protected String sql;

        /** 内部的なJDBCマネジャ */
        protected JdbcManagerImplementor implementor;

        /**
         * インスタンスを構築します。
         * 
         * @param path
         *            SQLファイルのパス
         */
        public SqlFile(String path) {
            implementor = JdbcManagerImplementor.class.cast(jdbcManager);
            this.sql = getSql(path);
        }

        /**
         * SQLを返します。
         * 
         * @param path
         *            SQLファイルのパス
         * @return SQL
         */
        protected String getSql(String path) {
            if (path.endsWith(".sql")) {
                path = path.substring(0, path.length() - 4);
            }
            String dbmsName = implementor.getDialect().getName();
            if (dbmsName != null) {
                String sql = readSql(path + "_" + dbmsName);
                if (sql != null) {
                    return sql;
                }
            }
            String sql = readSql(path);
            if (sql != null) {
                return sql;
            }
            throw new ResourceNotFoundRuntimeException(path);
        }

        /**
         * SQLをファイルから読み取ります。
         * 
         * @param path
         *            SQLファイルのパス
         * @return SQL
         */
        protected String readSql(String path) {
            InputStream is = ResourceUtil.getResourceAsStreamNoException(path,
                    "sql");
            if (is == null) {
                return null;
            }
            Reader reader = InputStreamReaderUtil.create(is, "UTF-8");
            String sql = ReaderUtil.readText(reader);
            if (sql.length() > 0 && sql.charAt(0) == '\uFEFF') {
                sql = sql.substring(1);
            }
            sql = sql.trim();
            if (sql.endsWith(";")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            return sql;
        }

        /**
         * SQLを実行します。
         */
        public void execute() {
            System.out.println(sql);
            Connection connection = org.seasar.extension.jdbc.util.DataSourceUtil
                    .getConnection(implementor.getDataSource());
            try {
                PreparedStatement ps = ConnectionUtil.prepareStatement(
                        connection, sql);
                try {
                    PreparedStatementUtil.execute(ps);
                } finally {
                    StatementUtil.close(ps);
                }
            } finally {
                ConnectionUtil.close(connection);
            }
        }
    }
}