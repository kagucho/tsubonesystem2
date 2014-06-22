package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TPartySendMail;
import tsuboneSystem.names.TMailNames._TMailNames;
import tsuboneSystem.names.TPartyNames._TPartyNames;

/**
 * {@link TPartySendMail}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/06/22 19:38:34")
public class TPartySendMailNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * PartyIdのプロパティ名を返します。
     * 
     * @return PartyIdのプロパティ名
     */
    public static PropertyName<Integer> PartyId() {
        return new PropertyName<Integer>("PartyId");
    }

    /**
     * MailIdのプロパティ名を返します。
     * 
     * @return MailIdのプロパティ名
     */
    public static PropertyName<Integer> MailId() {
        return new PropertyName<Integer>("MailId");
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
    public static class _TPartySendMailNames extends PropertyName<TPartySendMail> {

        /**
         * インスタンスを構築します。
         */
        public _TPartySendMailNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TPartySendMailNames(final String name) {
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
        public _TPartySendMailNames(final PropertyName<?> parent, final String name) {
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
         * PartyIdのプロパティ名を返します。
         *
         * @return PartyIdのプロパティ名
         */
        public PropertyName<Integer> PartyId() {
            return new PropertyName<Integer>(this, "PartyId");
        }

        /**
         * MailIdのプロパティ名を返します。
         *
         * @return MailIdのプロパティ名
         */
        public PropertyName<Integer> MailId() {
            return new PropertyName<Integer>(this, "MailId");
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
         * tMailのプロパティ名を返します。
         * 
         * @return tMailのプロパティ名
         */
        public _TMailNames tMail() {
            return new _TMailNames(this, "tMail");
        }
    }
}