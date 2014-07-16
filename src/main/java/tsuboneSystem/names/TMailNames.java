package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.names.TBbsDetailNames._TBbsDetailNames;
import tsuboneSystem.names.TBbsSubjectNames._TBbsSubjectNames;
import tsuboneSystem.names.TMailSendMemberNames._TMailSendMemberNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TPartySendMailNames._TPartySendMailNames;

/**
 * {@link TMail}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/07/15 13:30:51")
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
     * errorFlagのプロパティ名を返します。
     * 
     * @return errorFlagのプロパティ名
     */
    public static PropertyName<Boolean> errorFlag() {
        return new PropertyName<Boolean>("errorFlag");
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
     * tPartySendMailのプロパティ名を返します。
     * 
     * @return tPartySendMailのプロパティ名
     */
    public static _TPartySendMailNames tPartySendMail() {
        return new _TPartySendMailNames("tPartySendMail");
    }

    /**
     * tBbsSubjectのプロパティ名を返します。
     * 
     * @return tBbsSubjectのプロパティ名
     */
    public static _TBbsSubjectNames tBbsSubject() {
        return new _TBbsSubjectNames("tBbsSubject");
    }

    /**
     * tBbsDetailのプロパティ名を返します。
     * 
     * @return tBbsDetailのプロパティ名
     */
    public static _TBbsDetailNames tBbsDetail() {
        return new _TBbsDetailNames("tBbsDetail");
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
         * errorFlagのプロパティ名を返します。
         *
         * @return errorFlagのプロパティ名
         */
        public PropertyName<Boolean> errorFlag() {
            return new PropertyName<Boolean>(this, "errorFlag");
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

        /**
         * tPartySendMailのプロパティ名を返します。
         * 
         * @return tPartySendMailのプロパティ名
         */
        public _TPartySendMailNames tPartySendMail() {
            return new _TPartySendMailNames(this, "tPartySendMail");
        }

        /**
         * tBbsSubjectのプロパティ名を返します。
         * 
         * @return tBbsSubjectのプロパティ名
         */
        public _TBbsSubjectNames tBbsSubject() {
            return new _TBbsSubjectNames(this, "tBbsSubject");
        }

        /**
         * tBbsDetailのプロパティ名を返します。
         * 
         * @return tBbsDetailのプロパティ名
         */
        public _TBbsDetailNames tBbsDetail() {
            return new _TBbsDetailNames(this, "tBbsDetail");
        }
    }
}