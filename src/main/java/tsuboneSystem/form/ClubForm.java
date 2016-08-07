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
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;
import org.seasar.struts.annotation.UrlType;

import tsuboneSystem.entity.TClub;
import tsuboneSystem.entity.TLeaders;
import tsuboneSystem.entity.TMember;
import tsuboneSystem.entity.TMemberClub;

@Component(instance = InstanceType.SESSION)
public class ClubForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Integer id;
	
	/* 部名　*/
	@Required
	@Maxlength(maxlength=10)
	public String ClubName;
	
	/* 部長のID　*/
	@Required
	public String OfficerId;
	
	/* 部の概要　*/
	@Required
	public String ClubMemo;
	
	/* 部の概要　*/
	@UrlType
	public String clubUrl;
	
	/* 削除フラグ　*/
	public String deleteFlag;
	
	/* 部長　*/
	public TLeaders tLeaders;
	
	/** Clubのリスト */
	public List<TClub> clubItems;
	
	/** Clubのマップ */
	public List<Map<Integer,String>> clubMap;	
	
	/** memberのマップ */
	public Map<String,String> memberMap;
	
	/** memberのマップ(Integer String) */
	public Map<Integer,String> memberMapIS;
	
	/** 選択した項目のパラメータ　*/
	public String[] club_checks = new String[0];
	
	/** 部の代表者の情報を格納する　*/
	public TMember tMember;
	
	/** 部に所属している人のリスト　*/
	public List<TMemberClub> tMemberClubList;
	
	/** 部に所属している人のリスト*/
	public List<TMember> tMemberList;
	
	/** 全メンバーのリスト　*/
	public List<TMember> tMemberAllList;
	
	//以下メール送信関係
		/** メールの送信相手のリスト　*/
		public List<TMember> tMemberSendList;
		
		/* メールの送信者のID　*/
		public Integer registMemberId;
		
		/* メールのタイトル　*/
		@Required(target = "confirmMail")
		public String title;

		/* メールの内容　*/
		@Required(target = "confirmMail")
		public String content;
	
	//リッセットメソッド(※命名注意！！"reset"にすると、このformに関わるすべてのメソッドで呼び出される。)
	public void resetInput() {
		ClubName = null;
		deleteFlag = null;
		OfficerId = null;
		ClubMemo = null;
		tLeaders =null;
    }

}
