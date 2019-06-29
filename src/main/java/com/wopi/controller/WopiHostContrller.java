package com.wopi.controller;

import com.wopi.model.FileInfo;
import com.wopi.util.Sha256Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Wopi文件预览
 *
 * @author CaoYongCheng
 */
@RestController
@RequestMapping(value = "/wopi")
public class WopiHostContrller {

    @Value("${file.path}")
    private String filePath;

    /**
     * 获取文件流
     * <p>
     * office online server 读取文件流
     *
     * @param name     文件名称
     * @param response 文件流
     */
    @GetMapping("/files/{name}/contents")
    public void getFile(@PathVariable(name = "name") String name, HttpServletResponse response) {
        System.out.println("GET获取文件啦!!!!");
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            // 文件的路径
            String path = filePath + name;
            File file = new File(path);
            // 取得文件名
            String filename = file.getName();
            // 以流的形式下载文件
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // 清空response
            response.reset();

            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            System.out.println("GET获取文件Contents结束!!!!");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件信息
     *
     * @param name 文件名称
     */
    @GetMapping(value = "/files/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<?> getFileInfo(@PathVariable String name) {
        System.out.println("获取文件啦!!!!");
        FileInfo info = new FileInfo();
        try {
            // 获取文件名, 防止中文文件名乱码
            String fileName = URLDecoder.decode(name, StandardCharsets.UTF_8.name());
            if (fileName == null || fileName.length() == 0) {
                return ResponseEntity.notFound().build();
            }
            File file = new File(filePath + fileName);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
            // 取得文件名
            info.setBaseFileName(file.getName());
            info.setSize(file.length());
            info.setOwnerId("admin");
            info.setVersion(file.lastModified());
            info.setSha256(getHash256(file));
            info.setAllowExternalMarketplace(true);
            info.setUserCanWrite(true);
            info.setSupportsUpdate(true);
            info.setSupportsLocks(true);

            return ResponseEntity.ok(info);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 获取文件的SHA-256值
     *
     * @param file 文件
     * @return 文件SHA-256
     */
    private static String getHash256(File file) {
        return Sha256Utils.computeFileSha256(file);
    }
}