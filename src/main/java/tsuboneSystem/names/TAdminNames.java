package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TAdmin;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TAdmin}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/09/18 6:30:28")
public class TAdminNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * OfficerKindのプロパティ名を返します。
     * 
     * @return OfficerKindのプロパティ名
     */
    public static PropertyName<Integer> OfficerKind() {
        return new PropertyName<Integer>("OfficerKind");
    }

    /**
     * MemberIdのプロパティ名を返します。
     * 
     * @return MemberIdのプロパティ名
     */
    public static PropertyName<Integer> MemberId() {
        return new PropertyName<Integer>("MemberId");
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
    public static class _TAdminNames extends PropertyName<TAdmin> {

        /**
         * インスタンスを構築します。
         */
        public _TAdminNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TAdminNames(final String name) {
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
        public _TAdminNames(final PropertyName<?> parent, final String name) {
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
         * OfficerKindのプロパティ名を返します。
         *
         * @return OfficerKindのプロパティ名
         */
        public PropertyName<Integer> OfficerKind() {
            return new PropertyName<Integer>(this, "OfficerKind");
        }

        /**
         * MemberIdのプロパティ名を返します。
         *
         * @return MemberIdのプロパティ名
         */
        public PropertyName<Integer> MemberId() {
            return new PropertyName<Integer>(this, "MemberId");
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