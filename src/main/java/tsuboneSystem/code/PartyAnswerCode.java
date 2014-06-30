package tsuboneSystem.code;

import org.apache.commons.lang3.StringUtils;


public enum PartyAnswerCode implements CodeEnum {
	
	NO_SEND("0","メール送信はなし"),
	QUESTIONER("1","質問者にメールを送信"),
	ALL("2","質問者を含む、会議の出席者全員");

	
	private String code;
	private String name;
	
	private PartyAnswerCode(String code, String name){
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
		for(PartyAnswerCode element : PartyAnswerCode.values()){
			if (element.getCode().equals(code) ) {
				return element.getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCodeByName(String name){
		for(PartyAnswerCode element : PartyAnswerCode.values()){
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
