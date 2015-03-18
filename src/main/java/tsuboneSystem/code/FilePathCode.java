package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;


public enum FilePathCode implements CodeEnum {
	
	HONBAN_IMAGE("1","/usr/local/image/"),
	HONBAN_SUBMIT("1","/usr/local/submit/"),
	TEST_IMAGE("1","/Users/Hiroaki/Google/image/"),
	TEST_SUBMIT("1","/Users/Hiroaki/Google/submit/");
	
	private String code;
	private String name;
	
	private FilePathCode(String code, String name){
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
		for(FilePathCode element : FilePathCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(FilePathCode element : FilePathCode.values()){
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
