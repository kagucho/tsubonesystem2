package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;


public enum LeadersKindCode implements CodeEnum {
	
	CHIEF("1","局長"),
	SUB_CHIEF("2","副局長"),
	ACCOUNT("3","会計"),
	DIRECTOR("4","部長"),
	RIDAISAI("5","理大祭実行委員"),
	GASSYUKU("6","合宿実行委員"),
	WEBADMIN("7","システム管理者");
	
	private String code;
	private String name;
	
	private LeadersKindCode(String code, String name){
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
		for(LeadersKindCode element : LeadersKindCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(LeadersKindCode element : LeadersKindCode.values()){
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
