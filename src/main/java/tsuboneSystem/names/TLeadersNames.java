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
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.names.TClubNames._TClubNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;

/**
 * {@link TLeaders}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
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
     * secretInformationのプロパティ名を返します。
     * 
     * @return secretInformationのプロパティ名
     */
    public static PropertyName<Boolean> secretInformation() {
        return new PropertyName<Boolean>("secretInformation");
    }

    /**
     * memberUpdateのプロパティ名を返します。
     * 
     * @return memberUpdateのプロパティ名
     */
    public static PropertyName<Boolean> memberUpdate() {
        return new PropertyName<Boolean>("memberUpdate");
    }

    /**
     * attendUpdateのプロパティ名を返します。
     * 
     * @return attendUpdateのプロパティ名
     */
    public static PropertyName<Boolean> attendUpdate() {
        return new PropertyName<Boolean>("attendUpdate");
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
         * secretInformationのプロパティ名を返します。
         *
         * @return secretInformationのプロパティ名
         */
        public PropertyName<Boolean> secretInformation() {
            return new PropertyName<Boolean>(this, "secretInformation");
        }

        /**
         * memberUpdateのプロパティ名を返します。
         *
         * @return memberUpdateのプロパティ名
         */
        public PropertyName<Boolean> memberUpdate() {
            return new PropertyName<Boolean>(this, "memberUpdate");
        }

        /**
         * attendUpdateのプロパティ名を返します。
         *
         * @return attendUpdateのプロパティ名
         */
        public PropertyName<Boolean> attendUpdate() {
            return new PropertyName<Boolean>(this, "attendUpdate");
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
    }
}