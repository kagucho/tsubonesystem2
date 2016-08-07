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
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TBbsDetail;
import tsuboneSystem.entity.TBbsSubject;

@Component(instance = InstanceType.SESSION) 
public class BbsForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** id　*/
	public Integer id;
	
	/** スレッド名 */
	@Required(target = "bbsRegist")
	@Maxlength(maxlength=100)
	public String title;
	
	/** 内容 */
	@Required(target = "bbsDetailRegist")
	@Maxlength(maxlength=1000)
    public String detail;
    
    /* スレッド一覧 */
    public List<TBbsSubject> tBbsSubjectList;
    
    /* 各スレッドに投稿された内容一覧 */
    public List<TBbsDetail> tBbsDetailList;
    
  //リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
  	public void resetInput() {
  		id = null;
  		title = null;
  		detail = null;
  	}
		
}
