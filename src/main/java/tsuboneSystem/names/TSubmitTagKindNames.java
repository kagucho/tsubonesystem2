package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TSubmitTagKind;
import tsuboneSystem.names.TSubmitNames._TSubmitNames;

/**
 * {@link TSubmitTagKind}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/12/16 1:19:28")
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