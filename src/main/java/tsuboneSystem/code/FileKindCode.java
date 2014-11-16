package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;

/**
 * ファイルの拡張子
 * 
 * */
public enum FileKindCode implements CodeEnum {
	
	PDF("1","pdf"),
	JPG("2","jpg"),
	JPEG("3","jpeg"),
	PNG("4","png");
	
	private String code;
	private String name;
	
	private FileKindCode(String code, String name){
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
		for(FileKindCode element : FileKindCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(FileKindCode element : FileKindCode.values()){
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
}
