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
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/21 13:08:53")
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