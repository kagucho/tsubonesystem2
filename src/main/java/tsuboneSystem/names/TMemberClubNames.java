package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.names.TClubNames._TClubNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TMemberClub}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/11/03 20:26:12")
public class TMemberClubNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
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
     * ClubIdのプロパティ名を返します。
     * 
     * @return ClubIdのプロパティ名
     */
    public static PropertyName<Integer> ClubId() {
        return new PropertyName<Integer>("ClubId");
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
    public static class _TMemberClubNames extends PropertyName<TMemberClub> {

        /**
         * インスタンスを構築します。
         */
        public _TMemberClubNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TMemberClubNames(final String name) {
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
        public _TMemberClubNames(final PropertyName<?> parent, final String name) {
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
         * MemberIdのプロパティ名を返します。
         *
         * @return MemberIdのプロパティ名
         */
        public PropertyName<Integer> MemberId() {
            return new PropertyName<Integer>(this, "MemberId");
        }

        /**
         * ClubIdのプロパティ名を返します。
         *
         * @return ClubIdのプロパティ名
         */
        public PropertyName<Integer> ClubId() {
            return new PropertyName<Integer>(this, "ClubId");
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
         * tMemberのプロパティ名を返します。
         * 
         * @return tMemberのプロパティ名
         */
        public _TMemberNames tMember() {
            return new _TMemberNames(this, "tMember");
        }
    }
}