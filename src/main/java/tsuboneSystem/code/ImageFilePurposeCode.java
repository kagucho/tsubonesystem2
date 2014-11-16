package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;

/**
 * 画像ファイルの使用用途
 * 
 * */
public enum ImageFilePurposeCode implements CodeEnum {
	
	TOP_BACK("1","TOP画面背景"),
	TOP_ANNOUNCE("2","TOP画面おしらせ");
	
	private String code;
	private String name;
	
	private ImageFilePurposeCode(String code, String name){
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
		for(ImageFilePurposeCode element : ImageFilePurposeCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(ImageFilePurposeCode element : ImageFilePurposeCode.values()){
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
