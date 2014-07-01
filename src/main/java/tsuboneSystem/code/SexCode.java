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
