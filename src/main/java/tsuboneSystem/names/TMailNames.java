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

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TMail;
import tsuboneSystem.names.TBbsDetailNames._TBbsDetailNames;
import tsuboneSystem.names.TBbsSubjectNames._TBbsSubjectNames;
import tsuboneSystem.names.TMailSendMemberNames._TMailSendMemberNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TPartySendMailNames._TPartySendMailNames;

/**
 * {@link TMail}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/05/23 19:17:46")
public class TMailNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * registMemberIdのプロパティ名を返します。
     * 
     * @return registMemberIdのプロパティ名
     */
    public static PropertyName<Integer> registMemberId() {
        return new PropertyName<Integer>("registMemberId");
    }

    /**
     * titleのプロパティ名を返します。
     * 
     * @return titleのプロパティ名
     */
    public static PropertyName<String> title() {
        return new PropertyName<String>("title");
    }

    /**
     * contentのプロパティ名を返します。
     * 
     * @return contentのプロパティ名
     */
    public static PropertyName<String> content() {
        return new PropertyName<String>("content");
    }

    /**
     * errorFlagのプロパティ名を返します。
     * 
     * @return errorFlagのプロパティ名
     */
    public static PropertyName<Boolean> errorFlag() {
        return new PropertyName<Boolean>("errorFlag");
    }

    /**
     * browsingRightsのプロパティ名を返します。
     * 
     * @return browsingRightsのプロパティ名
     */
    public static PropertyName<Integer> browsingRights() {
        return new PropertyName<Integer>("browsingRights");
    }

    /**
     * registTimeのプロパティ名を返します。
     * 
     * @return registTimeのプロパティ名
     */
    public static PropertyName<Date> registTime() {
        return new PropertyName<Date>("registTime");
    }

    /**
     * tMailSendMemberのプロパティ名を返します。
     * 
     * @return tMailSendMemberのプロパティ名
     */
    public static _TMailSendMemberNames tMailSendMember() {
        return new _TMailSendMemberNames("tMailSendMember");
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
     * tPartySendMailのプロパティ名を返します。
     * 
     * @return tPartySendMailのプロパティ名
     */
    public static _TPartySendMailNames tPartySendMail() {
        return new _TPartySendMailNames("tPartySendMail");
    }

    /**
     * tBbsSubjectのプロパティ名を返します。
     * 
     * @return tBbsSubjectのプロパティ名
     */
    public static _TBbsSubjectNames tBbsSubject() {
        return new _TBbsSubjectNames("tBbsSubject");
    }

    /**
     * tBbsDetailのプロパティ名を返します。
     * 
     * @return tBbsDetailのプロパティ名
     */
    public static _TBbsDetailNames tBbsDetail() {
        return new _TBbsDetailNames("tBbsDetail");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TMailNames extends PropertyName<TMail> {

        /**
         * インスタンスを構築します。
         */
        public _TMailNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TMailNames(final String name) {
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
        public _TMailNames(final PropertyName<?> parent, final String name) {
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
         * registMemberIdのプロパティ名を返します。
         *
         * @return registMemberIdのプロパティ名
         */
        public PropertyName<Integer> registMemberId() {
            return new PropertyName<Integer>(this, "registMemberId");
        }

        /**
         * titleのプロパティ名を返します。
         *
         * @return titleのプロパティ名
         */
        public PropertyName<String> title() {
            return new PropertyName<String>(this, "title");
        }

        /**
         * contentのプロパティ名を返します。
         *
         * @return contentのプロパティ名
         */
        public PropertyName<String> content() {
            return new PropertyName<String>(this, "content");
        }

        /**
         * errorFlagのプロパティ名を返します。
         *
         * @return errorFlagのプロパティ名
         */
        public PropertyName<Boolean> errorFlag() {
            return new PropertyName<Boolean>(this, "errorFlag");
        }

        /**
         * browsingRightsのプロパティ名を返します。
         *
         * @return browsingRightsのプロパティ名
         */
        public PropertyName<Integer> browsingRights() {
            return new PropertyName<Integer>(this, "browsingRights");
        }

        /**
         * registTimeのプロパティ名を返します。
         *
         * @return registTimeのプロパティ名
         */
        public PropertyName<Date> registTime() {
            return new PropertyName<Date>(this, "registTime");
        }

        /**
         * tMailSendMemberのプロパティ名を返します。
         * 
         * @return tMailSendMemberのプロパティ名
         */
        public _TMailSendMemberNames tMailSendMember() {
            return new _TMailSendMemberNames(this, "tMailSendMember");
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
         * tPartySendMailのプロパティ名を返します。
         * 
         * @return tPartySendMailのプロパティ名
         */
        public _TPartySendMailNames tPartySendMail() {
            return new _TPartySendMailNames(this, "tPartySendMail");
        }

        /**
         * tBbsSubjectのプロパティ名を返します。
         * 
         * @return tBbsSubjectのプロパティ名
         */
        public _TBbsSubjectNames tBbsSubject() {
            return new _TBbsSubjectNames(this, "tBbsSubject");
        }

        /**
         * tBbsDetailのプロパティ名を返します。
         * 
         * @return tBbsDetailのプロパティ名
         */
        public _TBbsDetailNames tBbsDetail() {
            return new _TBbsDetailNames(this, "tBbsDetail");
        }
    }
}