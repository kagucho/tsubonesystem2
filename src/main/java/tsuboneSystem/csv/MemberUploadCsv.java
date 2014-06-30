package tsuboneSystem.csv;

import org.apache.commons.lang3.text.StrBuilder;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVMask;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

@CSVEntity(header=true)
public class MemberUploadCsv {
	/* ハンドルネーム　*/
	@CSVRequired
	@CSVMaxLength(maxlength=100)
	@CSVColumn(columnIndex=0)
	public String hname;
	
	/* メールアドレス　*/
	@CSVRequired
	@CSVColumn(columnIndex=1)
	public String mail;
	
	/* ID　*/
	@CSVRequired
	@CSVMask(mask = "^[\u0020-\u007E]+$")
	@CSVMaxLength(maxlength=100)
	@CSVColumn(columnIndex=2)
	public String userName;
	
	/* パスワード　*/
	@CSVRequired
	@CSVMask(mask = "^[\u0020-\u007E]+$")
	@CSVMaxLength(maxlength=100)
	@CSVColumn(columnIndex=3)
	public String password;
	
	@Override
	public String toString() {
		StrBuilder string = new StrBuilder();
		string.append("hname:");
		string.append(hname);
		string.append("    mail:");
		string.append(mail);
		string.append("    userName:");
		string.append(userName);
		string.append("    pw:");
		string.append(password);
		return string.toString();
	}
}
