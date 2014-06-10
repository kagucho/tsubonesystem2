package tsuboneSystem.code;

import org.apache.commons.lang.StringUtils;


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
	
	public int getCodeNumber(){
		if (StringUtils.isNumeric(code)) {
			return Integer.parseInt(code);
		}
		return 0;
	}
}
