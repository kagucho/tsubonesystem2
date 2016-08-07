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

package tsuboneSystem.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TTempLogin;

@Component(instance = InstanceType.SESSION)
public class SettingsEditForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 一時メンバーID */
	@Required(target = "tempMemberConfirm")
	public String userName;
	
	/** 一時メンバーpassword */
	public String password;
	
	/** 規約PDF */
	public FormFile rulePdf;
	
	/** 背景画像一覧 */
	public List<TImageUpload> tImageUploadList = new ArrayList<TImageUpload>();
	
	/** 画像ID */
	public Integer imageId;
	
	public List<TTempLogin> tTempLoginNow = new ArrayList<TTempLogin>();
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
    }

}
