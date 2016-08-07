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
import tsuboneSystem.entity.TSubmitTagKind;
import tsuboneSystem.names.TSubmitNames._TSubmitNames;

/**
 * {@link TSubmitTagKind}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
public class TSubmitTagKindNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * submitTagNameのプロパティ名を返します。
     * 
     * @return submitTagNameのプロパティ名
     */
    public static PropertyName<String> submitTagName() {
        return new PropertyName<String>("submitTagName");
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
     * tSubmitListのプロパティ名を返します。
     * 
     * @return tSubmitListのプロパティ名
     */
    public static _TSubmitNames tSubmitList() {
        return new _TSubmitNames("tSubmitList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TSubmitTagKindNames extends PropertyName<TSubmitTagKind> {

        /**
         * インスタンスを構築します。
         */
        public _TSubmitTagKindNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TSubmitTagKindNames(final String name) {
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
        public _TSubmitTagKindNames(final PropertyName<?> parent, final String name) {
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
         * submitTagNameのプロパティ名を返します。
         *
         * @return submitTagNameのプロパティ名
         */
        public PropertyName<String> submitTagName() {
            return new PropertyName<String>(this, "submitTagName");
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
         * tSubmitListのプロパティ名を返します。
         * 
         * @return tSubmitListのプロパティ名
         */
        public _TSubmitNames tSubmitList() {
            return new _TSubmitNames(this, "tSubmitList");
        }
    }
}