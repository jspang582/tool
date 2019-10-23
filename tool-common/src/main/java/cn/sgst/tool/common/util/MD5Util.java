package cn.sgst.tool.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类（封装jdk自带的md5加密方法）
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2019/7/26 9:54
 */
public class MD5Util {
    public static String encrypt(String source) {
        return encodeMd5(source.getBytes());
    }


    public static String encrypt(String source, int depth) {
        if(depth < 1){
            throw new IllegalArgumentException("depth cannot less than 1");
        }
        if (depth == 1) {
            return encrypt(source);
        } else {
            return encrypt(encrypt(source, depth - 1));
        }
    }

    private static String encodeMd5(byte[] source) {
        try {
            return encodeHex(MessageDigest.getInstance("MD5").digest(source));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static String encodeHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10)
                buffer.append("0");
            buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin", 0));
    }
}
