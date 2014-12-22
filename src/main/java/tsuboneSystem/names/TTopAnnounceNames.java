package tsuboneSystem.names;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TTopAnnounce;
import tsuboneSystem.names.TImageUploadNames._TImageUploadNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TSubmitNames._TSubmitNames;

/**
 * {@link TTopAnnounce}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/12/17 0:57:40")
public class TTopAnnounceNames {

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
     * imageIdのプロパティ名を返します。
     * 
     * @return imageIdのプロパティ名
     */
    public static PropertyName<Integer> imageId() {
        return new PropertyName<Integer>("imageId");
    }

    /**
     * announceTitleのプロパティ名を返します。
     * 
     * @return announceTitleのプロパティ名
     */
    public static PropertyName<String> announceTitle() {
        return new PropertyName<String>("announceTitle");
    }

    /**
     * announceContentのプロパティ名を返します。
     * 
     * @return announceContentのプロパティ名
     */
    public static PropertyName<String> announceContent() {
        return new PropertyName<String>("announceContent");
    }

    /**
     * announceFromDayのプロパティ名を返します。
     * 
     * @return announceFromDayのプロパティ名
     */
    public static PropertyName<Date> announceFromDay() {
        return new PropertyName<Date>("announceFromDay");
    }

    /**
     * announceToDayのプロパティ名を返します。
     * 
     * @return announceToDayのプロパティ名
     */
    public static PropertyName<Date> announceToDay() {
        return new PropertyName<Date>("announceToDay");
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
     * tImageUploadのプロパティ名を返します。
     * 
     * @return tImageUploadのプロパティ名
     */
    public static _TImageUploadNames tImageUpload() {
        return new _TImageUploadNames("tImageUpload");
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
    public static class _TTopAnnounceNames extends PropertyName<TTopAnnounce> {

        /**
         * インスタンスを構築します。
         */
        public _TTopAnnounceNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TTopAnnounceNames(final String name) {
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
        public _TTopAnnounceNames(final PropertyName<?> parent, final String name) {
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
         * imageIdのプロパティ名を返します。
         *
         * @return imageIdのプロパティ名
         */
        public PropertyName<Integer> imageId() {
            return new PropertyName<Integer>(this, "imageId");
        }

        /**
         * announceTitleのプロパティ名を返します。
         *
         * @return announceTitleのプロパティ名
         */
        public PropertyName<String> announceTitle() {
            return new PropertyName<String>(this, "announceTitle");
        }

        /**
         * announceContentのプロパティ名を返します。
         *
         * @return announceContentのプロパティ名
         */
        public PropertyName<String> announceContent() {
            return new PropertyName<String>(this, "announceContent");
        }

        /**
         * announceFromDayのプロパティ名を返します。
         *
         * @return announceFromDayのプロパティ名
         */
        public PropertyName<Date> announceFromDay() {
            return new PropertyName<Date>(this, "announceFromDay");
        }

        /**
         * announceToDayのプロパティ名を返します。
         *
         * @return announceToDayのプロパティ名
         */
        public PropertyName<Date> announceToDay() {
            return new PropertyName<Date>(this, "announceToDay");
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
         * tImageUploadのプロパティ名を返します。
         * 
         * @return tImageUploadのプロパティ名
         */
        public _TImageUploadNames tImageUpload() {
            return new _TImageUploadNames(this, "tImageUpload");
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