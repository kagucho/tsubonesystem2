/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
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