package com.wopi.util;


import com.common.exception.runtime.DataValidateException;
import com.common.filter.GlobalErrorMessage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author CaoYongCheng
 */
public class Sha256Utils {
    private static final String SHA256 = "SHA-256";

    private Sha256Utils() {
    }

    public static String computeFileSha256(File file) {
        byte[] bytes = file2Bytes(file);
        return MessageDigestUtil.getMD5Digest(bytes, SHA256);
    }

    public static String computeSha256(byte[] bytes) {
        return MessageDigestUtil.getMD5Digest(bytes, SHA256);
    }

    private static byte[] file2Bytes(File file) {
        if (file == null) {
            throw new DataValidateException(GlobalErrorMessage.ARGUMENT_VALID_ERROR);
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = fis.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw new DataValidateException(GlobalErrorMessage.INTERNAL_SERVER_ERROR);
        }

    }
}
