package tsuboneSystem.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TBbsDetail;
import tsuboneSystem.names.TBbsSubjectNames._TBbsSubjectNames;
import tsuboneSystem.names.TMailNames._TMailNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TBbsDetail}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/10/02 1:02:07")
public class TBbsDetailNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * subjectIdのプロパティ名を返します。
     * 
     * @return subjectIdのプロパティ名
     */
    public static PropertyName<Integer> subjectId() {
        return new PropertyName<Integer>("subjectId");
    }

    /**
     * detailのプロパティ名を返します。
     * 
     * @return detailのプロパティ名
     */
    public static PropertyName<String> detail() {
        return new PropertyName<String>("detail");
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
     * tMemberのプロパティ名を返します。
     * 
     * @return tMemberのプロパティ名
     */
    public static _TMemberNames tMember() {
        return new _TMemberNames("tMember");
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
    public static class _TBbsDetailNames extends PropertyName<TBbsDetail> {

        /**
         * インスタンスを構築します。
         */
        public _TBbsDetailNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TBbsDetailNames(final String name) {
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
        public _TBbsDetailNames(final PropertyName<?> parent, final String name) {
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
         * subjectIdのプロパティ名を返します。
         *
         * @return subjectIdのプロパティ名
         */
        public PropertyName<Integer> subjectId() {
            return new PropertyName<Integer>(this, "subjectId");
        }

        /**
         * detailのプロパティ名を返します。
         *
         * @return detailのプロパティ名
         */
        public PropertyName<String> detail() {
            return new PropertyName<String>(this, "detail");
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
         * tMemberのプロパティ名を返します。
         * 
         * @return tMemberのプロパティ名
         */
        public _TMemberNames tMember() {
            return new _TMemberNames(this, "tMember");
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
         * tMailのプロパティ名を返します。
         * 
         * @return tMailのプロパティ名
         */
        public _TMailNames tMail() {
            return new _TMailNames(this, "tMail");
        }
    }
}