package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TEnquete;
import tsuboneSystem.names.TEnqueteSelectNames._TEnqueteSelectNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TEnquete}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/11/03 20:26:12")
public class TEnqueteNames {

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
     * memoのプロパティ名を返します。
     * 
     * @return memoのプロパティ名
     */
    public static PropertyName<String> memo() {
        return new PropertyName<String>("memo");
    }

    /**
     * createIdのプロパティ名を返します。
     * 
     * @return createIdのプロパティ名
     */
    public static PropertyName<Integer> createId() {
        return new PropertyName<Integer>("createId");
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
     * tMemberのプロパティ名を返します。
     * 
     * @return tMemberのプロパティ名
     */
    public static _TMemberNames tMember() {
        return new _TMemberNames("tMember");
    }

    /**
     * tEnqueteSelectのプロパティ名を返します。
     * 
     * @return tEnqueteSelectのプロパティ名
     */
    public static _TEnqueteSelectNames tEnqueteSelect() {
        return new _TEnqueteSelectNames("tEnqueteSelect");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TEnqueteNames extends PropertyName<TEnquete> {

        /**
         * インスタンスを構築します。
         */
        public _TEnqueteNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TEnqueteNames(final String name) {
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
        public _TEnqueteNames(final PropertyName<?> parent, final String name) {
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
         * memoのプロパティ名を返します。
         *
         * @return memoのプロパティ名
         */
        public PropertyName<String> memo() {
            return new PropertyName<String>(this, "memo");
        }

        /**
         * createIdのプロパティ名を返します。
         *
         * @return createIdのプロパティ名
         */
        public PropertyName<Integer> createId() {
            return new PropertyName<Integer>(this, "createId");
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
         * tMemberのプロパティ名を返します。
         * 
         * @return tMemberのプロパティ名
         */
        public _TMemberNames tMember() {
            return new _TMemberNames(this, "tMember");
        }

        /**
         * tEnqueteSelectのプロパティ名を返します。
         * 
         * @return tEnqueteSelectのプロパティ名
         */
        public _TEnqueteSelectNames tEnqueteSelect() {
            return new _TEnqueteSelectNames(this, "tEnqueteSelect");
        }
    }
}