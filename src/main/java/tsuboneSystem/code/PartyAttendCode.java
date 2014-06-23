package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;


public enum PartyAttendCode implements CodeEnum {
	
	UNSUBMITTED("0","未提出"),
	YES_ATTEND("1","出席"),
	NO_ATTEND("2","欠席");

	
	private String code;
	private String name;
	
	private PartyAttendCode(String code, String name){
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
		for(PartyAttendCode element : PartyAttendCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(PartyAttendCode element : PartyAttendCode.values()){
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
