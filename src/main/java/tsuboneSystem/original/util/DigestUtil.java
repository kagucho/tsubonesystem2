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

package tsuboneSystem.original.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

	/**
     * 入力された文字列をMD5でハッシュして、ダイジェスト文字列を返します。
     * @param src ソース文字列
     * @return ダイジェスト文字列
     * @throws NoSuchAlgorithmException
     */
    @SuppressWarnings("boxing")
	public static String md5(String src) {
        
        String rtn = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            byte[] digest = md.digest();
 
            //ダイジェストを文字列に変換します。
            for (int i = 0; i < digest.length; i++) {
                rtn += String.format("%02x", digest[i]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return rtn;
    }
	
}
