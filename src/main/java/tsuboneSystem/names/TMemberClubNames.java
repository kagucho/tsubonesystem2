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
import tsuboneSystem.entity.TMemberClub;
import tsuboneSystem.names.TClubNames._TClubNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TMemberClub}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
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