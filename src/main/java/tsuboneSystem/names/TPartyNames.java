package tsuboneSystem.names;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TParty;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TPartyClubNames._TPartyClubNames;

/**
 * {@link TParty}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/06/09 14:20:33")
public class TPartyNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * creatorIdのプロパティ名を返します。
     * 
     * @return creatorIdのプロパティ名
     */
    public static PropertyName<Integer> creatorId() {
        return new PropertyName<Integer>("creatorId");
    }

    /**
     * meetingNameのプロパティ名を返します。
     * 
     * @return meetingNameのプロパティ名
     */
    public static PropertyName<String> meetingName() {
        return new PropertyName<String>("meetingName");
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
     * meetingDayのプロパティ名を返します。
     * 
     * @return meetingDayのプロパティ名
     */
    public static PropertyName<Date> meetingDay() {
        return new PropertyName<Date>("meetingDay");
    }

    /**
     * meetingTimeのプロパティ名を返します。
     * 
     * @return meetingTimeのプロパティ名
     */
    public static PropertyName<Date> meetingTime() {
        return new PropertyName<Date>("meetingTime");
    }

    /**
     * meetingRoomのプロパティ名を返します。
     * 
     * @return meetingRoomのプロパティ名
     */
    public static PropertyName<String> meetingRoom() {
        return new PropertyName<String>("meetingRoom");
    }

    /**
     * meetingMemoのプロパティ名を返します。
     * 
     * @return meetingMemoのプロパティ名
     */
    public static PropertyName<String> meetingMemo() {
        return new PropertyName<String>("meetingMemo");
    }

    /**
     * meetingDeadlineDayのプロパティ名を返します。
     * 
     * @return meetingDeadlineDayのプロパティ名
     */
    public static PropertyName<Date> meetingDeadlineDay() {
        return new PropertyName<Date>("meetingDeadlineDay");
    }

    /**
     * meetingDeadlineTimeのプロパティ名を返します。
     * 
     * @return meetingDeadlineTimeのプロパティ名
     */
    public static PropertyName<Date> meetingDeadlineTime() {
        return new PropertyName<Date>("meetingDeadlineTime");
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
     * deleteFlagのプロパティ名を返します。
     * 
     * @return deleteFlagのプロパティ名
     */
    public static PropertyName<Boolean> deleteFlag() {
        return new PropertyName<Boolean>("deleteFlag");
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
     * tPartyClubListのプロパティ名を返します。
     * 
     * @return tPartyClubListのプロパティ名
     */
    public static _TPartyClubNames tPartyClubList() {
        return new _TPartyClubNames("tPartyClubList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TPartyNames extends PropertyName<TParty> {

        /**
         * インスタンスを構築します。
         */
        public _TPartyNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TPartyNames(final String name) {
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
        public _TPartyNames(final PropertyName<?> parent, final String name) {
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
         * creatorIdのプロパティ名を返します。
         *
         * @return creatorIdのプロパティ名
         */
        public PropertyName<Integer> creatorId() {
            return new PropertyName<Integer>(this, "creatorId");
        }

        /**
         * meetingNameのプロパティ名を返します。
         *
         * @return meetingNameのプロパティ名
         */
        public PropertyName<String> meetingName() {
            return new PropertyName<String>(this, "meetingName");
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
         * meetingDayのプロパティ名を返します。
         *
         * @return meetingDayのプロパティ名
         */
        public PropertyName<Date> meetingDay() {
            return new PropertyName<Date>(this, "meetingDay");
        }

        /**
         * meetingTimeのプロパティ名を返します。
         *
         * @return meetingTimeのプロパティ名
         */
        public PropertyName<Date> meetingTime() {
            return new PropertyName<Date>(this, "meetingTime");
        }

        /**
         * meetingRoomのプロパティ名を返します。
         *
         * @return meetingRoomのプロパティ名
         */
        public PropertyName<String> meetingRoom() {
            return new PropertyName<String>(this, "meetingRoom");
        }

        /**
         * meetingMemoのプロパティ名を返します。
         *
         * @return meetingMemoのプロパティ名
         */
        public PropertyName<String> meetingMemo() {
            return new PropertyName<String>(this, "meetingMemo");
        }

        /**
         * meetingDeadlineDayのプロパティ名を返します。
         *
         * @return meetingDeadlineDayのプロパティ名
         */
        public PropertyName<Date> meetingDeadlineDay() {
            return new PropertyName<Date>(this, "meetingDeadlineDay");
        }

        /**
         * meetingDeadlineTimeのプロパティ名を返します。
         *
         * @return meetingDeadlineTimeのプロパティ名
         */
        public PropertyName<Date> meetingDeadlineTime() {
            return new PropertyName<Date>(this, "meetingDeadlineTime");
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
         * deleteFlagのプロパティ名を返します。
         *
         * @return deleteFlagのプロパティ名
         */
        public PropertyName<Boolean> deleteFlag() {
            return new PropertyName<Boolean>(this, "deleteFlag");
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
         * tPartyClubListのプロパティ名を返します。
         * 
         * @return tPartyClubListのプロパティ名
         */
        public _TPartyClubNames tPartyClubList() {
            return new _TPartyClubNames(this, "tPartyClubList");
        }
    }
}