package com.kilogod.code.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Anding
 * @describe
 */
public class MD5Utils {
    public static final String SALT = "1qazxsw2";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pwd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pwd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String pwd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pwd, ByteSource.Util.bytes(username + SALT),HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String md5(String str){
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] bytes=md.digest(str.getBytes());

            String res= Base64.getEncoder().encodeToString(bytes);

            return res;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getMD5String(String str) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        int md5length=md5code.length();
        for (int i = 0; i < 32 - md5length; i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("17868986878","123456"));
    }
}
