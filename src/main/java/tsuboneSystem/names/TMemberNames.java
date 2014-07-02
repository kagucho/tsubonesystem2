package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.names.TLeadersNames._TLeadersNames;
import tsuboneSystem.names.TMailNames._TMailNames;
import tsuboneSystem.names.TMailSendMemberNames._TMailSendMemberNames;
import tsuboneSystem.names.TPartyAnswerNames._TPartyAnswerNames;
import tsuboneSystem.names.TPartyAttendNames._TPartyAttendNames;
import tsuboneSystem.names.TPartyNames._TPartyNames;
import tsuboneSystem.names.TPartyQuestionNames._TPartyQuestionNames;

/**
 * {@link TMember}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/07/01 23:34:20")
public class TMemberNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * nameのプロパティ名を返します。
     * 
     * @return nameのプロパティ名
     */
    public static PropertyName<String> name() {
        return new PropertyName<String>("name");
    }

    /**
     * hnameのプロパティ名を返します。
     * 
     * @return hnameのプロパティ名
     */
    public static PropertyName<String> hname() {
        return new PropertyName<String>("hname");
    }

    /**
     * sexのプロパティ名を返します。
     * 
     * @return sexのプロパティ名
     */
    public static PropertyName<String> sex() {
        return new PropertyName<String>("sex");
    }

    /**
     * mailのプロパティ名を返します。
     * 
     * @return mailのプロパティ名
     */
    public static PropertyName<String> mail() {
        return new PropertyName<String>("mail");
    }

    /**
     * curriculumのプロパティ名を返します。
     * 
     * @return curriculumのプロパティ名
     */
    public static PropertyName<String> curriculum() {
        return new PropertyName<String>("curriculum");
    }

    /**
     * entranceのプロパティ名を返します。
     * 
     * @return entranceのプロパティ名
     */
    public static PropertyName<Integer> entrance() {
        return new PropertyName<Integer>("entrance");
    }

    /**
     * tel1のプロパティ名を返します。
     * 
     * @return tel1のプロパティ名
     */
    public static PropertyName<String> tel1() {
        return new PropertyName<String>("tel1");
    }

    /**
     * tel2のプロパティ名を返します。
     * 
     * @return tel2のプロパティ名
     */
    public static PropertyName<String> tel2() {
        return new PropertyName<String>("tel2");
    }

    /**
     * tel3のプロパティ名を返します。
     * 
     * @return tel3のプロパティ名
     */
    public static PropertyName<String> tel3() {
        return new PropertyName<String>("tel3");
    }

    /**
     * userNameのプロパティ名を返します。
     * 
     * @return userNameのプロパティ名
     */
    public static PropertyName<String> userName() {
        return new PropertyName<String>("userName");
    }

    /**
     * passwordのプロパティ名を返します。
     * 
     * @return passwordのプロパティ名
     */
    public static PropertyName<String> password() {
        return new PropertyName<String>("password");
    }

    /**
     * obFlagのプロパティ名を返します。
     * 
     * @return obFlagのプロパティ名
     */
    public static PropertyName<Boolean> obFlag() {
        return new PropertyName<Boolean>("obFlag");
    }

    /**
     * sendErrorFlagのプロパティ名を返します。
     * 
     * @return sendErrorFlagのプロパティ名
     */
    public static PropertyName<Boolean> sendErrorFlag() {
        return new PropertyName<Boolean>("sendErrorFlag");
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
     * tLeadersListのプロパティ名を返します。
     * 
     * @return tLeadersListのプロパティ名
     */
    public static _TLeadersNames tLeadersList() {
        return new _TLeadersNames("tLeadersList");
    }

    /**
     * tPaertAttendListのプロパティ名を返します。
     * 
     * @return tPaertAttendListのプロパティ名
     */
    public static _TPartyAttendNames tPaertAttendList() {
        return new _TPartyAttendNames("tPaertAttendList");
    }

    /**
     * tPaertyListのプロパティ名を返します。
     * 
     * @return tPaertyListのプロパティ名
     */
    public static _TPartyNames tPaertyList() {
        return new _TPartyNames("tPaertyList");
    }

    /**
     * tMailSendMemberListのプロパティ名を返します。
     * 
     * @return tMailSendMemberListのプロパティ名
     */
    public static _TMailSendMemberNames tMailSendMemberList() {
        return new _TMailSendMemberNames("tMailSendMemberList");
    }

    /**
     * tMailListのプロパティ名を返します。
     * 
     * @return tMailListのプロパティ名
     */
    public static _TMailNames tMailList() {
        return new _TMailNames("tMailList");
    }

    /**
     * tPartyQuestionListのプロパティ名を返します。
     * 
     * @return tPartyQuestionListのプロパティ名
     */
    public static _TPartyQuestionNames tPartyQuestionList() {
        return new _TPartyQuestionNames("tPartyQuestionList");
    }

    /**
     * tPartyAnswerListのプロパティ名を返します。
     * 
     * @return tPartyAnswerListのプロパティ名
     */
    public static _TPartyAnswerNames tPartyAnswerList() {
        return new _TPartyAnswerNames("tPartyAnswerList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TMemberNames extends PropertyName<TMember> {

        /**
         * インスタンスを構築します。
         */
        public _TMemberNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TMemberNames(final String name) {
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
        public _TMemberNames(final PropertyName<?> parent, final String name) {
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
         * nameのプロパティ名を返します。
         *
         * @return nameのプロパティ名
         */
        public PropertyName<String> name() {
            return new PropertyName<String>(this, "name");
        }

        /**
         * hnameのプロパティ名を返します。
         *
         * @return hnameのプロパティ名
         */
        public PropertyName<String> hname() {
            return new PropertyName<String>(this, "hname");
        }

        /**
         * sexのプロパティ名を返します。
         *
         * @return sexのプロパティ名
         */
        public PropertyName<String> sex() {
            return new PropertyName<String>(this, "sex");
        }

        /**
         * mailのプロパティ名を返します。
         *
         * @return mailのプロパティ名
         */
        public PropertyName<String> mail() {
            return new PropertyName<String>(this, "mail");
        }

        /**
         * curriculumのプロパティ名を返します。
         *
         * @return curriculumのプロパティ名
         */
        public PropertyName<String> curriculum() {
            return new PropertyName<String>(this, "curriculum");
        }

        /**
         * entranceのプロパティ名を返します。
         *
         * @return entranceのプロパティ名
         */
        public PropertyName<Integer> entrance() {
            return new PropertyName<Integer>(this, "entrance");
        }

        /**
         * tel1のプロパティ名を返します。
         *
         * @return tel1のプロパティ名
         */
        public PropertyName<String> tel1() {
            return new PropertyName<String>(this, "tel1");
        }

        /**
         * tel2のプロパティ名を返します。
         *
         * @return tel2のプロパティ名
         */
        public PropertyName<String> tel2() {
            return new PropertyName<String>(this, "tel2");
        }

        /**
         * tel3のプロパティ名を返します。
         *
         * @return tel3のプロパティ名
         */
        public PropertyName<String> tel3() {
            return new PropertyName<String>(this, "tel3");
        }

        /**
         * userNameのプロパティ名を返します。
         *
         * @return userNameのプロパティ名
         */
        public PropertyName<String> userName() {
            return new PropertyName<String>(this, "userName");
        }

        /**
         * passwordのプロパティ名を返します。
         *
         * @return passwordのプロパティ名
         */
        public PropertyName<String> password() {
            return new PropertyName<String>(this, "password");
        }

        /**
         * obFlagのプロパティ名を返します。
         *
         * @return obFlagのプロパティ名
         */
        public PropertyName<Boolean> obFlag() {
            return new PropertyName<Boolean>(this, "obFlag");
        }

        /**
         * sendErrorFlagのプロパティ名を返します。
         *
         * @return sendErrorFlagのプロパティ名
         */
        public PropertyName<Boolean> sendErrorFlag() {
            return new PropertyName<Boolean>(this, "sendErrorFlag");
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
         * tLeadersListのプロパティ名を返します。
         * 
         * @return tLeadersListのプロパティ名
         */
        public _TLeadersNames tLeadersList() {
            return new _TLeadersNames(this, "tLeadersList");
        }

        /**
         * tPaertAttendListのプロパティ名を返します。
         * 
         * @return tPaertAttendListのプロパティ名
         */
        public _TPartyAttendNames tPaertAttendList() {
            return new _TPartyAttendNames(this, "tPaertAttendList");
        }

        /**
         * tPaertyListのプロパティ名を返します。
         * 
         * @return tPaertyListのプロパティ名
         */
        public _TPartyNames tPaertyList() {
            return new _TPartyNames(this, "tPaertyList");
        }

        /**
         * tMailSendMemberListのプロパティ名を返します。
         * 
         * @return tMailSendMemberListのプロパティ名
         */
        public _TMailSendMemberNames tMailSendMemberList() {
            return new _TMailSendMemberNames(this, "tMailSendMemberList");
        }

        /**
         * tMailListのプロパティ名を返します。
         * 
         * @return tMailListのプロパティ名
         */
        public _TMailNames tMailList() {
            return new _TMailNames(this, "tMailList");
        }

        /**
         * tPartyQuestionListのプロパティ名を返します。
         * 
         * @return tPartyQuestionListのプロパティ名
         */
        public _TPartyQuestionNames tPartyQuestionList() {
            return new _TPartyQuestionNames(this, "tPartyQuestionList");
        }

        /**
         * tPartyAnswerListのプロパティ名を返します。
         * 
         * @return tPartyAnswerListのプロパティ名
         */
        public _TPartyAnswerNames tPartyAnswerList() {
            return new _TPartyAnswerNames(this, "tPartyAnswerList");
        }
    }
}