package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TPartySettings;
import tsuboneSystem.names.TPartyNames._TPartyNames;

/**
 * {@link TPartySettings}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/10/02 1:02:07")
public class TPartySettingsNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * partyIdのプロパティ名を返します。
     * 
     * @return partyIdのプロパティ名
     */
    public static PropertyName<Integer> partyId() {
        return new PropertyName<Integer>("partyId");
    }

    /**
     * meetingNecessaryFlagのプロパティ名を返します。
     * 
     * @return meetingNecessaryFlagのプロパティ名
     */
    public static PropertyName<Boolean> meetingNecessaryFlag() {
        return new PropertyName<Boolean>("meetingNecessaryFlag");
    }

    /**
     * ObAttendFlagのプロパティ名を返します。
     * 
     * @return ObAttendFlagのプロパティ名
     */
    public static PropertyName<Boolean> ObAttendFlag() {
        return new PropertyName<Boolean>("ObAttendFlag");
    }

    /**
     * resultEditEndFlagのプロパティ名を返します。
     * 
     * @return resultEditEndFlagのプロパティ名
     */
    public static PropertyName<Boolean> resultEditEndFlag() {
        return new PropertyName<Boolean>("resultEditEndFlag");
    }

    /**
     * noPublicFlagのプロパティ名を返します。
     * 
     * @return noPublicFlagのプロパティ名
     */
    public static PropertyName<Boolean> noPublicFlag() {
        return new PropertyName<Boolean>("noPublicFlag");
    }

    /**
     * noticeMemberNumのプロパティ名を返します。
     * 
     * @return noticeMemberNumのプロパティ名
     */
    public static PropertyName<Integer> noticeMemberNum() {
        return new PropertyName<Integer>("noticeMemberNum");
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
     * tPartyのプロパティ名を返します。
     * 
     * @return tPartyのプロパティ名
     */
    public static _TPartyNames tParty() {
        return new _TPartyNames("tParty");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TPartySettingsNames extends PropertyName<TPartySettings> {

        /**
         * インスタンスを構築します。
         */
        public _TPartySettingsNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TPartySettingsNames(final String name) {
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
        public _TPartySettingsNames(final PropertyName<?> parent, final String name) {
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
         * partyIdのプロパティ名を返します。
         *
         * @return partyIdのプロパティ名
         */
        public PropertyName<Integer> partyId() {
            return new PropertyName<Integer>(this, "partyId");
        }

        /**
         * meetingNecessaryFlagのプロパティ名を返します。
         *
         * @return meetingNecessaryFlagのプロパティ名
         */
        public PropertyName<Boolean> meetingNecessaryFlag() {
            return new PropertyName<Boolean>(this, "meetingNecessaryFlag");
        }

        /**
         * ObAttendFlagのプロパティ名を返します。
         *
         * @return ObAttendFlagのプロパティ名
         */
        public PropertyName<Boolean> ObAttendFlag() {
            return new PropertyName<Boolean>(this, "ObAttendFlag");
        }

        /**
         * resultEditEndFlagのプロパティ名を返します。
         *
         * @return resultEditEndFlagのプロパティ名
         */
        public PropertyName<Boolean> resultEditEndFlag() {
            return new PropertyName<Boolean>(this, "resultEditEndFlag");
        }

        /**
         * noPublicFlagのプロパティ名を返します。
         *
         * @return noPublicFlagのプロパティ名
         */
        public PropertyName<Boolean> noPublicFlag() {
            return new PropertyName<Boolean>(this, "noPublicFlag");
        }

        /**
         * noticeMemberNumのプロパティ名を返します。
         *
         * @return noticeMemberNumのプロパティ名
         */
        public PropertyName<Integer> noticeMemberNum() {
            return new PropertyName<Integer>(this, "noticeMemberNum");
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
         * tPartyのプロパティ名を返します。
         * 
         * @return tPartyのプロパティ名
         */
        public _TPartyNames tParty() {
            return new _TPartyNames(this, "tParty");
        }
    }
}