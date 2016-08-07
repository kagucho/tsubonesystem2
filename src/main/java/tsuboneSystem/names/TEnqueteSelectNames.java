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
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.names.TEnqueteAnswerNames._TEnqueteAnswerNames;
import tsuboneSystem.names.TEnqueteNames._TEnqueteNames;

/**
 * {@link TEnqueteSelect}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
public class TEnqueteSelectNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * enqueteIdのプロパティ名を返します。
     * 
     * @return enqueteIdのプロパティ名
     */
    public static PropertyName<Integer> enqueteId() {
        return new PropertyName<Integer>("enqueteId");
    }

    /**
     * selectedContentsのプロパティ名を返します。
     * 
     * @return selectedContentsのプロパティ名
     */
    public static PropertyName<String> selectedContents() {
        return new PropertyName<String>("selectedContents");
    }

    /**
     * tEnqueteのプロパティ名を返します。
     * 
     * @return tEnqueteのプロパティ名
     */
    public static _TEnqueteNames tEnquete() {
        return new _TEnqueteNames("tEnquete");
    }

    /**
     * tEnqueteAnswerListのプロパティ名を返します。
     * 
     * @return tEnqueteAnswerListのプロパティ名
     */
    public static _TEnqueteAnswerNames tEnqueteAnswerList() {
        return new _TEnqueteAnswerNames("tEnqueteAnswerList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TEnqueteSelectNames extends PropertyName<TEnqueteSelect> {

        /**
         * インスタンスを構築します。
         */
        public _TEnqueteSelectNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TEnqueteSelectNames(final String name) {
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
        public _TEnqueteSelectNames(final PropertyName<?> parent, final String name) {
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
         * enqueteIdのプロパティ名を返します。
         *
         * @return enqueteIdのプロパティ名
         */
        public PropertyName<Integer> enqueteId() {
            return new PropertyName<Integer>(this, "enqueteId");
        }

        /**
         * selectedContentsのプロパティ名を返します。
         *
         * @return selectedContentsのプロパティ名
         */
        public PropertyName<String> selectedContents() {
            return new PropertyName<String>(this, "selectedContents");
        }

        /**
         * tEnqueteのプロパティ名を返します。
         * 
         * @return tEnqueteのプロパティ名
         */
        public _TEnqueteNames tEnquete() {
            return new _TEnqueteNames(this, "tEnquete");
        }

        /**
         * tEnqueteAnswerListのプロパティ名を返します。
         * 
         * @return tEnqueteAnswerListのプロパティ名
         */
        public _TEnqueteAnswerNames tEnqueteAnswerList() {
            return new _TEnqueteAnswerNames(this, "tEnqueteAnswerList");
        }
    }
}