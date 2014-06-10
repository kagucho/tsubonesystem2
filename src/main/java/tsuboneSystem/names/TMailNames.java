package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.names.TMailSendMemberNames._TMailSendMemberNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TMail}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/06/10 23:55:07")
public class TMailNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * registMemberIdのプロパティ名を返します。
     * 
     * @return registMemberIdのプロパティ名
     */
    public static PropertyName<Integer> registMemberId() {
        return new PropertyName<Integer>("registMemberId");
    }

    /**
     * titleのプロパティ名を返します。
     * 
     * @return titleのプロパティ名
     */
    public static PropertyName<String> title() {
        return new PropertyName<String>("title");
    }

    /**
     * contentのプロパティ名を返します。
     * 
     * @return contentのプロパティ名
     */
    public static PropertyName<String> content() {
        return new PropertyName<String>("content");
    }

    /**
     * tMailSendMemberのプロパティ名を返します。
     * 
     * @return tMailSendMemberのプロパティ名
     */
    public static _TMailSendMemberNames tMailSendMember() {
        return new _TMailSendMemberNames("tMailSendMember");
    }

    /**
     * tMemberのプロパティ名を返します。
     * 
     * @return tMemberのプロパティ名
     */
    public static _TMemberNames tMember() {
        return new _TMemberNames("tMember");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TMailNames extends PropertyName<TMail> {

        /**
         * インスタンスを構築します。
         */
        public _TMailNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TMailNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _TMailNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<Integer> id() {
            return new PropertyName<Integer>(this, "id");
        }

        /**
         * registMemberIdのプロパティ名を返します。
         *
         * @return registMemberIdのプロパティ名
         */
        public PropertyName<Integer> registMemberId() {
            return new PropertyName<Integer>(this, "registMemberId");
        }

        /**
         * titleのプロパティ名を返します。
         *
         * @return titleのプロパティ名
         */
        public PropertyName<String> title() {
            return new PropertyName<String>(this, "title");
        }

        /**
         * contentのプロパティ名を返します。
         *
         * @return contentのプロパティ名
         */
        public PropertyName<String> content() {
            return new PropertyName<String>(this, "content");
        }

        /**
         * tMailSendMemberのプロパティ名を返します。
         * 
         * @return tMailSendMemberのプロパティ名
         */
        public _TMailSendMemberNames tMailSendMember() {
            return new _TMailSendMemberNames(this, "tMailSendMember");
        }

        /**
         * tMemberのプロパティ名を返します。
         * 
         * @return tMemberのプロパティ名
         */
        public _TMemberNames tMember() {
            return new _TMemberNames(this, "tMember");
        }
    }
}