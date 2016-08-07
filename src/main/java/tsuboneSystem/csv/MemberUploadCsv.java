/*
 * Copyright (C) 2014-2016  Kagucho <kagucho.net@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
