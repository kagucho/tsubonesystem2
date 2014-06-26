package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TPartyAttend;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TPartyNames._TPartyNames;

/**
 * {@link TPartyAttend}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/06/26 15:34:28")
public class TPartyAttendNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * partyIdのプロパティ名を返します。
     * 
     * @return partyIdのプロパティ名
     */
    public static PropertyName<Integer> partyId() {
        return new PropertyName<Integer>("partyId");
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
     * attendのプロパティ名を返します。
     * 
     * @return attendのプロパティ名
     */
    public static PropertyName<Integer> attend() {
        return new PropertyName<Integer>("attend");
    }

    /**
     * tPartyのプロパティ名を返します。
     * 
     * @return tPartyのプロパティ名
     */
    public static _TPartyNames tParty() {
        return new _TPartyNames("tParty");
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
    public static class _TPartyAttendNames extends PropertyName<TPartyAttend> {

        /**
         * インスタンスを構築します。
         */
        public _TPartyAttendNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TPartyAttendNames(final String name) {
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
        public _TPartyAttendNames(final PropertyName<?> parent, final String name) {
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
         * partyIdのプロパティ名を返します。
         *
         * @return partyIdのプロパティ名
         */
        public PropertyName<Integer> partyId() {
            return new PropertyName<Integer>(this, "partyId");
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
         * attendのプロパティ名を返します。
         *
         * @return attendのプロパティ名
         */
        public PropertyName<Integer> attend() {
            return new PropertyName<Integer>(this, "attend");
        }

        /**
         * tPartyのプロパティ名を返します。
         * 
         * @return tPartyのプロパティ名
         */
        public _TPartyNames tParty() {
            return new _TPartyNames(this, "tParty");
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