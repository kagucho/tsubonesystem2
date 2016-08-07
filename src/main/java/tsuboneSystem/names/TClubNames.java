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
import tsuboneSystem.entity.TClub;
import tsuboneSystem.names.TLeadersNames._TLeadersNames;
import tsuboneSystem.names.TMemberClubNames._TMemberClubNames;
import tsuboneSystem.names.TPartyClubNames._TPartyClubNames;

/**
 * {@link TClub}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
public class TClubNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * ClubNameのプロパティ名を返します。
     * 
     * @return ClubNameのプロパティ名
     */
    public static PropertyName<String> ClubName() {
        return new PropertyName<String>("ClubName");
    }

    /**
     * LeadersIdのプロパティ名を返します。
     * 
     * @return LeadersIdのプロパティ名
     */
    public static PropertyName<Integer> LeadersId() {
        return new PropertyName<Integer>("LeadersId");
    }

    /**
     * ClubMemoのプロパティ名を返します。
     * 
     * @return ClubMemoのプロパティ名
     */
    public static PropertyName<String> ClubMemo() {
        return new PropertyName<String>("ClubMemo");
    }

    /**
     * clubUrlのプロパティ名を返します。
     * 
     * @return clubUrlのプロパティ名
     */
    public static PropertyName<String> clubUrl() {
        return new PropertyName<String>("clubUrl");
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
     * tLeadersのプロパティ名を返します。
     * 
     * @return tLeadersのプロパティ名
     */
    public static _TLeadersNames tLeaders() {
        return new _TLeadersNames("tLeaders");
    }

    /**
     * tPartyClubListのプロパティ名を返します。
     * 
     * @return tPartyClubListのプロパティ名
     */
    public static _TPartyClubNames tPartyClubList() {
        return new _TPartyClubNames("tPartyClubList");
    }

    /**
     * tMemberClubListのプロパティ名を返します。
     * 
     * @return tMemberClubListのプロパティ名
     */
    public static _TMemberClubNames tMemberClubList() {
        return new _TMemberClubNames("tMemberClubList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TClubNames extends PropertyName<TClub> {

        /**
         * インスタンスを構築します。
         */
        public _TClubNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TClubNames(final String name) {
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
        public _TClubNames(final PropertyName<?> parent, final String name) {
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
         * ClubNameのプロパティ名を返します。
         *
         * @return ClubNameのプロパティ名
         */
        public PropertyName<String> ClubName() {
            return new PropertyName<String>(this, "ClubName");
        }

        /**
         * LeadersIdのプロパティ名を返します。
         *
         * @return LeadersIdのプロパティ名
         */
        public PropertyName<Integer> LeadersId() {
            return new PropertyName<Integer>(this, "LeadersId");
        }

        /**
         * ClubMemoのプロパティ名を返します。
         *
         * @return ClubMemoのプロパティ名
         */
        public PropertyName<String> ClubMemo() {
            return new PropertyName<String>(this, "ClubMemo");
        }

        /**
         * clubUrlのプロパティ名を返します。
         *
         * @return clubUrlのプロパティ名
         */
        public PropertyName<String> clubUrl() {
            return new PropertyName<String>(this, "clubUrl");
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
         * tLeadersのプロパティ名を返します。
         * 
         * @return tLeadersのプロパティ名
         */
        public _TLeadersNames tLeaders() {
            return new _TLeadersNames(this, "tLeaders");
        }

        /**
         * tPartyClubListのプロパティ名を返します。
         * 
         * @return tPartyClubListのプロパティ名
         */
        public _TPartyClubNames tPartyClubList() {
            return new _TPartyClubNames(this, "tPartyClubList");
        }

        /**
         * tMemberClubListのプロパティ名を返します。
         * 
         * @return tMemberClubListのプロパティ名
         */
        public _TMemberClubNames tMemberClubList() {
            return new _TMemberClubNames(this, "tMemberClubList");
        }
    }
}