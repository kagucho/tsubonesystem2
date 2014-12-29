package tsuboneSystem.names;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import tsuboneSystem.entity.TEnqueteSelect;
import tsuboneSystem.names.TEnqueteAnswerNames._TEnqueteAnswerNames;
import tsuboneSystem.names.TEnqueteNames._TEnqueteNames;

/**
 * {@link TEnqueteSelect}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/12/30 4:04:27")
public class TEnqueteSelectNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * enqueteIdのプロパティ名を返します。
     * 
     * @return enqueteIdのプロパティ名
     */
    public static PropertyName<Integer> enqueteId() {
        return new PropertyName<Integer>("enqueteId");
    }

    /**
     * selectedContentsのプロパティ名を返します。
     * 
     * @return selectedContentsのプロパティ名
     */
    public static PropertyName<String> selectedContents() {
        return new PropertyName<String>("selectedContents");
    }

    /**
     * tEnqueteのプロパティ名を返します。
     * 
     * @return tEnqueteのプロパティ名
     */
    public static _TEnqueteNames tEnquete() {
        return new _TEnqueteNames("tEnquete");
    }

    /**
     * tEnqueteAnswerListのプロパティ名を返します。
     * 
     * @return tEnqueteAnswerListのプロパティ名
     */
    public static _TEnqueteAnswerNames tEnqueteAnswerList() {
        return new _TEnqueteAnswerNames("tEnqueteAnswerList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TEnqueteSelectNames extends PropertyName<TEnqueteSelect> {

        /**
         * インスタンスを構築します。
         */
        public _TEnqueteSelectNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TEnqueteSelectNames(final String name) {
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
        public _TEnqueteSelectNames(final PropertyName<?> parent, final String name) {
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
         * enqueteIdのプロパティ名を返します。
         *
         * @return enqueteIdのプロパティ名
         */
        public PropertyName<Integer> enqueteId() {
            return new PropertyName<Integer>(this, "enqueteId");
        }

        /**
         * selectedContentsのプロパティ名を返します。
         *
         * @return selectedContentsのプロパティ名
         */
        public PropertyName<String> selectedContents() {
            return new PropertyName<String>(this, "selectedContents");
        }

        /**
         * tEnqueteのプロパティ名を返します。
         * 
         * @return tEnqueteのプロパティ名
         */
        public _TEnqueteNames tEnquete() {
            return new _TEnqueteNames(this, "tEnquete");
        }

        /**
         * tEnqueteAnswerListのプロパティ名を返します。
         * 
         * @return tEnqueteAnswerListのプロパティ名
         */
        public _TEnqueteAnswerNames tEnqueteAnswerList() {
            return new _TEnqueteAnswerNames(this, "tEnqueteAnswerList");
        }
    }
}