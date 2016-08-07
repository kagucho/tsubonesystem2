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




public enum SexCode implements CodeEnum {
	
	men("1","男"),
	woman("2","女"),
	onee("3","その他");
	
	private String code;
	private String name;
	
	private SexCode(String code, String name){
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
		for(SexCode element : SexCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	private static HashMap<String, String> sexCodeMap = null;
	
	@SuppressWarnings("unchecked")
	/**
	 * 性別のMAPを返す
	 * @return
	 */
	public static HashMap<String, String> getSexCodeMap() {
		//すでに値が入っているならそのまま返す
		if (sexCodeMap != null) {
			return (HashMap<String, String>) sexCodeMap.clone();
		}
		
		//まだ値が入っていないなら値を入れる
		sexCodeMap = new HashMap<String, String>();
		for (int i = 1; i <= 3; i++) {
			sexCodeMap.put(Integer.toString(i), SexCode.getnameByCode(Integer.toString(i)));
		}
		
		return (HashMap<String, String>) sexCodeMap.clone();
	}
	
	
	public int getCodeNumber(){
		if (StringUtils.isNumeric(code)) {
			return Integer.parseInt(code);
		}
		return 0;
	}
}
