package tsuboneSystem.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TBbsSubject;
import tsuboneSystem.names.TBbsDetailNames._TBbsDetailNames;
import tsuboneSystem.names.TMailNames._TMailNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TBbsSubject}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/10/06 16:48:45")
public class TBbsSubjectNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
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
     * memberIdのプロパティ名を返します。
     * 
     * @return memberIdのプロパティ名
     */
    public static PropertyName<Integer> memberId() {
        return new PropertyName<Integer>("memberId");
    }

    /**
     * mailIdのプロパティ名を返します。
     * 
     * @return mailIdのプロパティ名
     */
    public static PropertyName<Integer> mailId() {
        return new PropertyName<Integer>("mailId");
    }

    /**
     * updateTimeのプロパティ名を返します。
     * 
     * @return updateTimeのプロパティ名
     */
    public static PropertyName<Timestamp> updateTime() {
        return new PropertyName<Timestamp>("updateTime");
    }

    /**
     * deleteFlagのプロパティ名を返します。
     * 
     * @return deleteFlagのプロパティ名
     */
    public static PropertyName<Boolean> deleteFlag() {
        return new PropertyName<Boolean>("deleteFlag");
    }

    /**
     * tBbsDetailListのプロパティ名を返します。
     * 
     * @return tBbsDetailListのプロパティ名
     */
    public static _TBbsDetailNames tBbsDetailList() {
        return new _TBbsDetailNames("tBbsDetailList");
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
     * tMailのプロパティ名を返します。
     * 
     * @return tMailのプロパティ名
     */
    public static _TMailNames tMail() {
        return new _TMailNames("tMail");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TBbsSubjectNames extends PropertyName<TBbsSubject> {

        /**
         * インスタンスを構築します。
         */
        public _TBbsSubjectNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TBbsSubjectNames(final String name) {
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
        public _TBbsSubjectNames(final PropertyName<?> parent, final String name) {
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
         * titleのプロパティ名を返します。
         *
         * @return titleのプロパティ名
         */
        public PropertyName<String> title() {
            return new PropertyName<String>(this, "title");
        }

        /**
         * memberIdのプロパティ名を返します。
         *
         * @return memberIdのプロパティ名
         */
        public PropertyName<Integer> memberId() {
            return new PropertyName<Integer>(this, "memberId");
        }

        /**
         * mailIdのプロパティ名を返します。
         *
         * @return mailIdのプロパティ名
         */
        public PropertyName<Integer> mailId() {
            return new PropertyName<Integer>(this, "mailId");
        }

        /**
         * updateTimeのプロパティ名を返します。
         *
         * @return updateTimeのプロパティ名
         */
        public PropertyName<Timestamp> updateTime() {
            return new PropertyName<Timestamp>(this, "updateTime");
        }

        /**
         * deleteFlagのプロパティ名を返します。
         *
         * @return deleteFlagのプロパティ名
         */
        public PropertyName<Boolean> deleteFlag() {
            return new PropertyName<Boolean>(this, "deleteFlag");
        }

        /**
         * tBbsDetailListのプロパティ名を返します。
         * 
         * @return tBbsDetailListのプロパティ名
         */
        public _TBbsDetailNames tBbsDetailList() {
            return new _TBbsDetailNames(this, "tBbsDetailList");
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
         * tMailのプロパティ名を返します。
         * 
         * @return tMailのプロパティ名
         */
        public _TMailNames tMail() {
            return new _TMailNames(this, "tMail");
        }
    }
}