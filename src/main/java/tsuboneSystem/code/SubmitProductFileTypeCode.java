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

package tsuboneSystem.code;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

/**
 * ファイルの種類
 * 
 * */
public enum SubmitProductFileTypeCode implements CodeEnum {
	
	IMAGE("1","画像系"),
	PROGRAMS("2","プログラム系"),
	DTM("3","DTM系");
	
	private String code;
	private String name;
	
	private SubmitProductFileTypeCode(String code, String name){
		this.code = code;
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static String getnameByCode(String code){
		for(SubmitProductFileTypeCode element : SubmitProductFileTypeCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(SubmitProductFileTypeCode element : SubmitProductFileTypeCode.values()){
			if (element.getName().equals(name) ) {
				return element.getCode();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public int getCodeNumber(){
		if (StringUtils.isNumeric(code)) {
			return Integer.parseInt(code);
		}
		return 0;
	}
	
	private static HashMap<String, String> submitProductFileCodeMap = null;
	
	@SuppressWarnings("unchecked")
	/**
	 * ファイルの種類のMAPを返す
	 * @return
	 */
	public static HashMap<String, String> getSubmitProductFileCodeMap() {
		//すでに値が入っているならそのまま返す
		if (submitProductFileCodeMap != null) {
			return (HashMap<String, String>) submitProductFileCodeMap.clone();
		}
		
		//まだ値が入っていないなら値を入れる
		submitProductFileCodeMap = new HashMap<String, String>();
		for (int i = 1; i <= 3; i++) {
			submitProductFileCodeMap.put(Integer.toString(i), SubmitProductFileTypeCode.getnameByCode(Integer.toString(i)));
		}
		
		return (HashMap<String, String>) submitProductFileCodeMap.clone();
	}
}
