package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TLeadersKind;
import tsuboneSystem.names.TLeadersNames._TLeadersNames;

/**
 * {@link TLeadersKind}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/06/30 15:15:44")
public class TLeadersKindNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * KindNameのプロパティ名を返します。
     * 
     * @return KindNameのプロパティ名
     */
    public static PropertyName<String> KindName() {
        return new PropertyName<String>("KindName");
    }

    /**
     * KindMemoのプロパティ名を返します。
     * 
     * @return KindMemoのプロパティ名
     */
    public static PropertyName<String> KindMemo() {
        return new PropertyName<String>("KindMemo");
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
     * @author S2JDBC-Gen
     */
    public static class _TLeadersKindNames extends PropertyName<TLeadersKind> {

        /**
         * インスタンスを構築します。
         */
        public _TLeadersKindNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TLeadersKindNames(final String name) {
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
        public _TLeadersKindNames(final PropertyName<?> parent, final String name) {
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
         * KindNameのプロパティ名を返します。
         *
         * @return KindNameのプロパティ名
         */
        public PropertyName<String> KindName() {
            return new PropertyName<String>(this, "KindName");
        }

        /**
         * KindMemoのプロパティ名を返します。
         *
         * @return KindMemoのプロパティ名
         */
        public PropertyName<String> KindMemo() {
            return new PropertyName<String>(this, "KindMemo");
        }

        /**
         * tLeadersListのプロパティ名を返します。
         * 
         * @return tLeadersListのプロパティ名
         */
        public _TLeadersNames tLeadersList() {
            return new _TLeadersNames(this, "tLeadersList");
        }
    }
}