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
import tsuboneSystem.entity.TSubmit;
import tsuboneSystem.names.TImageUploadNames._TImageUploadNames;
import tsuboneSystem.names.TMemberNames._TMemberNames;
import tsuboneSystem.names.TSubmitTagKindNames._TSubmitTagKindNames;
import tsuboneSystem.names.TTopAnnounceNames._TTopAnnounceNames;

/**
 * {@link TSubmit}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/03/19 0:46:14")
public class TSubmitNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * registIdのプロパティ名を返します。
     * 
     * @return registIdのプロパティ名
     */
    public static PropertyName<Integer> registId() {
        return new PropertyName<Integer>("registId");
    }

    /**
     * topAnnounceIdのプロパティ名を返します。
     * 
     * @return topAnnounceIdのプロパティ名
     */
    public static PropertyName<Integer> topAnnounceId() {
        return new PropertyName<Integer>("topAnnounceId");
    }

    /**
     * submitTagKindIdのプロパティ名を返します。
     * 
     * @return submitTagKindIdのプロパティ名
     */
    public static PropertyName<Integer> submitTagKindId() {
        return new PropertyName<Integer>("submitTagKindId");
    }

    /**
     * submitNameのプロパティ名を返します。
     * 
     * @return submitNameのプロパティ名
     */
    public static PropertyName<String> submitName() {
        return new PropertyName<String>("submitName");
    }

    /**
     * submitDetailのプロパティ名を返します。
     * 
     * @return submitDetailのプロパティ名
     */
    public static PropertyName<String> submitDetail() {
        return new PropertyName<String>("submitDetail");
    }

    /**
     * submitCaptionImageIdのプロパティ名を返します。
     * 
     * @return submitCaptionImageIdのプロパティ名
     */
    public static PropertyName<Integer> submitCaptionImageId() {
        return new PropertyName<Integer>("submitCaptionImageId");
    }

    /**
     * submitProductFileNameのプロパティ名を返します。
     * 
     * @return submitProductFileNameのプロパティ名
     */
    public static PropertyName<String> submitProductFileName() {
        return new PropertyName<String>("submitProductFileName");
    }

    /**
     * submitProductFilePathのプロパティ名を返します。
     * 
     * @return submitProductFilePathのプロパティ名
     */
    public static PropertyName<String> submitProductFilePath() {
        return new PropertyName<String>("submitProductFilePath");
    }

    /**
     * soundCloudUrlのプロパティ名を返します。
     * 
     * @return soundCloudUrlのプロパティ名
     */
    public static PropertyName<String> soundCloudUrl() {
        return new PropertyName<String>("soundCloudUrl");
    }

    /**
     * submitProductFileTypeのプロパティ名を返します。
     * 
     * @return submitProductFileTypeのプロパティ名
     */
    public static PropertyName<Integer> submitProductFileType() {
        return new PropertyName<Integer>("submitProductFileType");
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
     * tTopAnnounceのプロパティ名を返します。
     * 
     * @return tTopAnnounceのプロパティ名
     */
    public static _TTopAnnounceNames tTopAnnounce() {
        return new _TTopAnnounceNames("tTopAnnounce");
    }

    /**
     * tSubmitTagKindのプロパティ名を返します。
     * 
     * @return tSubmitTagKindのプロパティ名
     */
    public static _TSubmitTagKindNames tSubmitTagKind() {
        return new _TSubmitTagKindNames("tSubmitTagKind");
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
     * @author S2JDBC-Gen
     */
    public static class _TSubmitNames extends PropertyName<TSubmit> {

        /**
         * インスタンスを構築します。
         */
        public _TSubmitNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TSubmitNames(final String name) {
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
        public _TSubmitNames(final PropertyName<?> parent, final String name) {
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
         * registIdのプロパティ名を返します。
         *
         * @return registIdのプロパティ名
         */
        public PropertyName<Integer> registId() {
            return new PropertyName<Integer>(this, "registId");
        }

        /**
         * topAnnounceIdのプロパティ名を返します。
         *
         * @return topAnnounceIdのプロパティ名
         */
        public PropertyName<Integer> topAnnounceId() {
            return new PropertyName<Integer>(this, "topAnnounceId");
        }

        /**
         * submitTagKindIdのプロパティ名を返します。
         *
         * @return submitTagKindIdのプロパティ名
         */
        public PropertyName<Integer> submitTagKindId() {
            return new PropertyName<Integer>(this, "submitTagKindId");
        }

        /**
         * submitNameのプロパティ名を返します。
         *
         * @return submitNameのプロパティ名
         */
        public PropertyName<String> submitName() {
            return new PropertyName<String>(this, "submitName");
        }

        /**
         * submitDetailのプロパティ名を返します。
         *
         * @return submitDetailのプロパティ名
         */
        public PropertyName<String> submitDetail() {
            return new PropertyName<String>(this, "submitDetail");
        }

        /**
         * submitCaptionImageIdのプロパティ名を返します。
         *
         * @return submitCaptionImageIdのプロパティ名
         */
        public PropertyName<Integer> submitCaptionImageId() {
            return new PropertyName<Integer>(this, "submitCaptionImageId");
        }

        /**
         * submitProductFileNameのプロパティ名を返します。
         *
         * @return submitProductFileNameのプロパティ名
         */
        public PropertyName<String> submitProductFileName() {
            return new PropertyName<String>(this, "submitProductFileName");
        }

        /**
         * submitProductFilePathのプロパティ名を返します。
         *
         * @return submitProductFilePathのプロパティ名
         */
        public PropertyName<String> submitProductFilePath() {
            return new PropertyName<String>(this, "submitProductFilePath");
        }

        /**
         * soundCloudUrlのプロパティ名を返します。
         *
         * @return soundCloudUrlのプロパティ名
         */
        public PropertyName<String> soundCloudUrl() {
            return new PropertyName<String>(this, "soundCloudUrl");
        }

        /**
         * submitProductFileTypeのプロパティ名を返します。
         *
         * @return submitProductFileTypeのプロパティ名
         */
        public PropertyName<Integer> submitProductFileType() {
            return new PropertyName<Integer>(this, "submitProductFileType");
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
         * tTopAnnounceのプロパティ名を返します。
         * 
         * @return tTopAnnounceのプロパティ名
         */
        public _TTopAnnounceNames tTopAnnounce() {
            return new _TTopAnnounceNames(this, "tTopAnnounce");
        }

        /**
         * tSubmitTagKindのプロパティ名を返します。
         * 
         * @return tSubmitTagKindのプロパティ名
         */
        public _TSubmitTagKindNames tSubmitTagKind() {
            return new _TSubmitTagKindNames(this, "tSubmitTagKind");
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
    }
}