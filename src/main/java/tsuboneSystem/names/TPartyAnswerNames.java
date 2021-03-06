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
import tsuboneSystem.entity.TPartyAnswer;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TPartyQuestionNames._TPartyQuestionNames;

/**
 * {@link TPartyAnswer}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
public class TPartyAnswerNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * questionIdのプロパティ名を返します。
     * 
     * @return questionIdのプロパティ名
     */
    public static PropertyName<Integer> questionId() {
        return new PropertyName<Integer>("questionId");
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
     * answerのプロパティ名を返します。
     * 
     * @return answerのプロパティ名
     */
    public static PropertyName<String> answer() {
        return new PropertyName<String>("answer");
    }

    /**
     * answerSendKindのプロパティ名を返します。
     * 
     * @return answerSendKindのプロパティ名
     */
    public static PropertyName<Integer> answerSendKind() {
        return new PropertyName<Integer>("answerSendKind");
    }

    /**
     * tPartyQuestionのプロパティ名を返します。
     * 
     * @return tPartyQuestionのプロパティ名
     */
    public static _TPartyQuestionNames tPartyQuestion() {
        return new _TPartyQuestionNames("tPartyQuestion");
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
    public static class _TPartyAnswerNames extends PropertyName<TPartyAnswer> {

        /**
         * インスタンスを構築します。
         */
        public _TPartyAnswerNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TPartyAnswerNames(final String name) {
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
        public _TPartyAnswerNames(final PropertyName<?> parent, final String name) {
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
         * questionIdのプロパティ名を返します。
         *
         * @return questionIdのプロパティ名
         */
        public PropertyName<Integer> questionId() {
            return new PropertyName<Integer>(this, "questionId");
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
         * answerのプロパティ名を返します。
         *
         * @return answerのプロパティ名
         */
        public PropertyName<String> answer() {
            return new PropertyName<String>(this, "answer");
        }

        /**
         * answerSendKindのプロパティ名を返します。
         *
         * @return answerSendKindのプロパティ名
         */
        public PropertyName<Integer> answerSendKind() {
            return new PropertyName<Integer>(this, "answerSendKind");
        }

        /**
         * tPartyQuestionのプロパティ名を返します。
         * 
         * @return tPartyQuestionのプロパティ名
         */
        public _TPartyQuestionNames tPartyQuestion() {
            return new _TPartyQuestionNames(this, "tPartyQuestion");
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