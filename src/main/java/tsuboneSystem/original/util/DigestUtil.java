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
