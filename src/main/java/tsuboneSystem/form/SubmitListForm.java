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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

import tsuboneSystem.entity.TSubmit;

@Component(instance = InstanceType.SESSION) 
public class SubmitListForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public List<TSubmit> list;
	
	 /** 作品分類タグ選択肢 */
    public Map<Integer, String> submitTagNameMap = new HashMap<Integer, String>();
    
    /** おしらせ */
    public Map<Integer, String> topAnnounceMap = new HashMap<Integer, String>();
}
