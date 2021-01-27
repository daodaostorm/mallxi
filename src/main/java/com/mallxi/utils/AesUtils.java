package com.mallxi.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Administrator
 *
 */
public class AesUtils {

	private static final String KEY = "ranchu.com666666";
    private static final String INITVector = "wozaiyule.999999";
	
	public static String byteToBase64(byte[] bytes) {

        byte[] encode = new Base64().encode(bytes);
        String result = new String(encode);
        return result;
    }
	
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INITVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return byteToBase64(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
    	try {
    		IvParameterSpec iv = new IvParameterSpec(INITVector.getBytes("UTF-8"));
    		SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
     
    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    		byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
     
    		return new String(original);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
     
    	return null;
    }
}