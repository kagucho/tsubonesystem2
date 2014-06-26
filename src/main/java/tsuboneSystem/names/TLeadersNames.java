package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.names.TClubNames._TClubNames;
import tsuboneSystem.names.TLeadersKindNames._TLeadersKindNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TLeaders}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/06/26 13:48:32")
public class TLeadersNames {

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
     * tClubのプロパティ名を返します。
     * 
     * @return tClubのプロパティ名
     */
    public static _TClubNames tClub() {
        return new _TClubNames("tClub");
    }

    /**
     * tLeadersKindのプロパティ名を返します。
     * 
     * @return tLeadersKindのプロパティ名
     */
    public static _TLeadersKindNames tLeadersKind() {
        return new _TLeadersKindNames("tLeadersKind");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TLeadersNames extends PropertyName<TLeaders> {

        /**
         * インスタンスを構築します。
         */
        public _TLeadersNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TLeadersNames(final String name) {
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
        public _TLeadersNames(final PropertyName<?> parent, final String name) {
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

        /**
         * tClubのプロパティ名を返します。
         * 
         * @return tClubのプロパティ名
         */
        public _TClubNames tClub() {
            return new _TClubNames(this, "tClub");
        }

        /**
         * tLeadersKindのプロパティ名を返します。
         * 
         * @return tLeadersKindのプロパティ名
         */
        public _TLeadersKindNames tLeadersKind() {
            return new _TLeadersKindNames(this, "tLeadersKind");
        }
    }
}