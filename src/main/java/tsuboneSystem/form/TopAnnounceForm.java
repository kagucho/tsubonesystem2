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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.code.FileKindCode;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.original.util.TsuboneSystemUtil;

@Component(instance = InstanceType.SESSION) 
public class TopAnnounceForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** お知らせの登録者Id */
	public Integer registMemberId;
	
	/** お知らせのタイトル */
	@Required
	public String announceTitle;
	
	/** お知らせの内容 */
	@Required
	public String announceContent;
	
	/** お知らせ掲載日(開始) */
	@DateType
	public String announceFromDay;
	
	/** お知らせ掲載日(終了) */
	@DateType
	public String announceToDay;
	
	/** 画像 */
	public FormFile file;

	/** お知らせの登録者 */
    public TMember tMember;
    
    /** 添付加増情報 */
    public TImageUpload tImageUpload;
    
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		id = null;
		registMemberId = null;
		announceTitle = null;
		announceContent = null;
		announceFromDay = null;
		announceToDay = null;
		if (file != null) {
			file.destroy();
		}
		tMember = null;
		tImageUpload = null;
	}
	
	//オリジナルチェック
    public ActionMessages validateBase(){
    	
    	ActionMessages errors = new ActionMessages();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		// ファイル種別確認
		String [] kinds = {FileKindCode.JPEG.getName(), FileKindCode.JPG.getName(),FileKindCode.PNG.getName()};
		if (file.getFileSize() > 0){
       	 if (TsuboneSystemUtil.isFileKindCheck(file, kinds)) {
            	errors.add("file",new ActionMessage("jpgファイル以外はアップロードできません",false));
            }
       }
		
		// お知らせ掲載日(開始日)は過去にはできない
		if (StringUtils.isNotEmpty(announceFromDay)) {
			try {
				Date mDay = sdf.parse(announceFromDay);
				if (mDay.before(new Date())) {
					errors.add("announceFromDay", new ActionMessage("過去に会議を予定したければタイムマシンを作ってからにしてください(今から掲示の場合は空白)", false));
				}
			} catch (ParseException e) {
				errors.add("announceFromDay", new ActionMessage("開催日はyyyy/mm/ddで入力する必要があります。(例:2014/05/11)", false));
			}
		}
		
		// お知らせ掲載日(終了日)は過去にはできない
		if (StringUtils.isNotEmpty(announceToDay)) {
			try {
				Date mDay = sdf.parse(announceToDay);
				if (mDay.before(new Date())) {
					errors.add("announceToDay", new ActionMessage("過去に会議を予定したければタイムマシンを作ってからにしてください", false));
				}
			} catch (ParseException e) {
				errors.add("announceToDay", new ActionMessage("開催日はyyyy/mm/ddで入力する必要があります。(例:2014/05/11)", false));
			}
		}
		return errors;
    }
}
