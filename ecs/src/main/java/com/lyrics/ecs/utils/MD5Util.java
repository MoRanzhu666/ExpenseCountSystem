package com.lyrics.ecs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类，提供计算字符串和文件的MD5哈希值的方法
 */
public class MD5Util {
    
    private static final String ALGORITHM = "MD5";
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    
    /**
     * 计算字符串的MD5哈希值
     * 
     * @param input 输入字符串
     * @return 小写的MD5哈希值字符串，如果发生异常则返回null
     */
    public static String getMD5(String input) {
        if (input == null) {
            return null;
        }
        
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("获取MD5算法实例失败: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 计算文件的MD5哈希值
     * 
     * @param file 要计算MD5的文件
     * @return 小写的MD5哈希值字符串，如果发生异常则返回null
     */
    public static String getFileMD5(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        
        try (FileInputStream fis = new FileInputStream(file)) {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            byte[] buffer = new byte[8192];
            int length;
            
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            
            byte[] bytes = md.digest();
            return bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("获取MD5算法实例失败: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("读取文件时发生错误: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 将字节数组转换为十六进制字符串
     * 
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int value = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_CHARS[value >>> 4];
            hexChars[i * 2 + 1] = HEX_CHARS[value & 0x0F];
        }
        return new String(hexChars);
    }
}
    