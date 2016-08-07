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

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.form.ImageUploadForm;
import tsuboneSystem.original.util.TsuboneSystemUtil;
import tsuboneSystem.service.TImageUploadService;

public class DisplyImageAction {
	
	HttpServletResponse response;
	
	/** ImageUploadActionのアクションフォーム */
	@ActionForm
	@Resource
	protected ImageUploadForm imageUploadForm;
	
	/** TImageUploadServiceのサービスクラス */
	@Resource
	protected TImageUploadService tImageUploadService;
	
	@Execute(validator = false, urlPattern = "{id}")
	public String index() throws IOException {

		//出力するファイル
		TImageUpload tImageUpload = tImageUploadService.findById(imageUploadForm.id);
		
		// 出力
		if (tImageUpload != null) {
			TsuboneSystemUtil.downloadCommon(tImageUpload.filePath, tImageUpload.fileName);
			return null;
		}
		
		return null;
    }
}
