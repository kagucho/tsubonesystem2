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
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Required;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TImageUpload;
import tsuboneSystem.entity.TTopAnnounce;

@Component(instance = InstanceType.SESSION) 
public class TopForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//質問者
	@Required(target = "contact")
	public String name;
	
	//質問者のメールアドレス
	@Required(target = "contact")
	@EmailType(target = "contact")
	public String mail;
	
	//質問(タイトル)
	@Required(target = "contact")
	public String subject;
	
	//質問(内容)
	@Required(target = "contact")
	public String message;
	
	//トップの部紹介一覧
	public List<TClub> clubList;
	
	//トップ背景画像一覧(画像のファイル名一覧)
	public List<TImageUpload> imageList;
	
	// おしらせ一覧
	public List<TTopAnnounce> topAnnounceList;
	
}
