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

package tsuboneSystem.action.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.UploadUtil;

import tsuboneSystem.code.FilePathCode;
import tsuboneSystem.code.ImageFilePurposeCode;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.form.ImageUploadForm;
import tsuboneSystem.service.TImageUploadService;

public class ImageUploadAction {
	
	/** ImageUploadActionのアクションフォーム */
	@ActionForm
	@Resource
	protected ImageUploadForm imageUploadForm;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
	@Resource
    HttpServletRequest req;

    @Execute(validator = false)
	public String index() {
    	
    	//ランダム文字を生成
    	String rm = RandomStringUtils.randomAlphabetic(10);
    	
    	//ファイルの格納先フォルダの絶対パスを取得(DBにこのパスを保存しておく)
    	String path = FilePathCode.HONBAN_SUBMIT.getName() + rm + imageUploadForm.file.getFileName();
    	
    	//ファイル書き込み（ファイルパスが空の場合は何もしません）
        UploadUtil.write(path, imageUploadForm.file);
        
        //ファイル名とファイルパスをDBに追加
    	TImageUpload imageUpload = new TImageUpload();
    	imageUpload.fileName = rm + imageUploadForm.file.getFileName();
    	imageUpload.filePath = path;
    	imageUpload.ImageFilePurpose = Integer.valueOf(ImageFilePurposeCode.TOP_BACK.getCode());
    	tImageUploadService.insert(imageUpload);
    	
        return null;
	}

}
