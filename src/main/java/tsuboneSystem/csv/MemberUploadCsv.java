package tsuboneSystem.csv;

import org.apache.commons.lang3.text.StrBuilder;
import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

@CSVEntity(header=true)
public class MemberUploadCsv {
	/* ハンドルネーム　*/
	@Required(msg=@Msg(key="errors.hname", resource=true))
	@Maxlength(maxlength=10)
	@CSVColumn(columnIndex=0)
	public String hname;
	
	/* メールアドレス　*/
	@Required(msg=@Msg(key="errors.mail", resource=true))
	@EmailType(msg=@Msg(key="errors.mailout", resource=true))
	@CSVColumn(columnIndex=1)
	public String mail;
	
	/* ID　*/
	@Required(msg=@Msg(key="errors.id", resource=true))
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=10)
	@CSVColumn(columnIndex=2)
	public String userName;
	
	/* パスワード　*/
	@Required(msg=@Msg(key="errors.password", resource=true),target = "confirm")
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=30)
	@CSVColumn(columnIndex=3)
	public String password;
	
	@Override
	public String toString() {
		StrBuilder string = new StrBuilder();
		string.append("hname;");
		string.append(hname);
		string.append("\t mail:");
		string.append(mail);
		string.append("\t userName:");
		string.append(userName);
		string.append("\t pw");
		string.append(password);
		return string.toString();
	}
}
