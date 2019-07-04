package com.wopi.util;


import com.common.exception.BaseRuntimeException;
import com.common.filter.GlobalErrorMessage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author CaoYongCheng
 */
public class DigestUtils {
    private static final String SHA256 = "SHA-256";
    private static final String MD5 = "MD5";

    private DigestUtils() {
    }

    public static String computeMd5(String content) {
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        return get5Digest(bytes, MD5);
    }

    public static String computeSha256(byte[] bytes) {
        return get5Digest(bytes, SHA256);
    }

    /**
     * 使用指定哈希算法计算摘要信息
     *
     * @param content   内容
     * @param algorithm 哈希算法
     * @return 内容摘要
     */
    private static String get5Digest(byte[] content, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(content);
            return bytesToHexString(messageDigest.digest());
        } catch (Exception e) {
            throw new BaseRuntimeException(GlobalErrorMessage.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param bytes 即将转换的数据
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(0xFF & aByte);
            if (temp.length() < 2) {
                sb.append(0);
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
