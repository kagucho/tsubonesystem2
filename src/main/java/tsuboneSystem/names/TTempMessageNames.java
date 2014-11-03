package tsuboneSystem.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TTempMessage;

/**
 * {@link TTempMessage}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/11/03 20:26:12")
public class TTempMessageNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * targetMemberIdのプロパティ名を返します。
     * 
     * @return targetMemberIdのプロパティ名
     */
    public static PropertyName<Integer> targetMemberId() {
        return new PropertyName<Integer>("targetMemberId");
    }

    /**
     * messageのプロパティ名を返します。
     * 
     * @return messageのプロパティ名
     */
    public static PropertyName<String> message() {
        return new PropertyName<String>("message");
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
     * registTimeのプロパティ名を返します。
     * 
     * @return registTimeのプロパティ名
     */
    public static PropertyName<Timestamp> registTime() {
        return new PropertyName<Timestamp>("registTime");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TTempMessageNames extends PropertyName<TTempMessage> {

        /**
         * インスタンスを構築します。
         */
        public _TTempMessageNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TTempMessageNames(final String name) {
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
        public _TTempMessageNames(final PropertyName<?> parent, final String name) {
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
         * targetMemberIdのプロパティ名を返します。
         *
         * @return targetMemberIdのプロパティ名
         */
        public PropertyName<Integer> targetMemberId() {
            return new PropertyName<Integer>(this, "targetMemberId");
        }

        /**
         * messageのプロパティ名を返します。
         *
         * @return messageのプロパティ名
         */
        public PropertyName<String> message() {
            return new PropertyName<String>(this, "message");
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
         * registTimeのプロパティ名を返します。
         *
         * @return registTimeのプロパティ名
         */
        public PropertyName<Timestamp> registTime() {
            return new PropertyName<Timestamp>(this, "registTime");
        }
    }
}