package tsuboneSystem.csv;

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
	public String hname;
	
	/* メールアドレス　*/
	@Required(msg=@Msg(key="errors.mail", resource=true))
	@EmailType(msg=@Msg(key="errors.mailout", resource=true))
	public String mail;
	
	/* ID　*/
	@Required(msg=@Msg(key="errors.id", resource=true))
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=10)
	public String userName;
	
	/* パスワード　*/
	@Required(msg=@Msg(key="errors.password", resource=true),target = "confirm")
	@Mask(mask = "^[\u0020-\u007E]+$", msg = @Msg(key = "errors.eisu", resource=true))
	@Maxlength(maxlength=30)
	public String password;
}
