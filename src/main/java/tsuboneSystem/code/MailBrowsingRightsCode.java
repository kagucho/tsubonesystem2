package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;


public enum MailBrowsingRightsCode implements CodeEnum {
	
	ADMIN("1","admin"),
	LEADERS("2","leaders"),
	MEMBER("3","individuals");

	
	private String code;
	private String name;
	
	private MailBrowsingRightsCode(String code, String name){
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
		for(MailBrowsingRightsCode element : MailBrowsingRightsCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(MailBrowsingRightsCode element : MailBrowsingRightsCode.values()){
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
