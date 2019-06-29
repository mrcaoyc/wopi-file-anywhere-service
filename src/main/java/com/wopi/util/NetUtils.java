package com.wopi.util;

import com.common.exception.runtime.DataNotFoundException;
import com.wopi.contants.FileErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/**
 * @author CaoYongCheng
 */
@Slf4j
public class NetUtils {
    /**
     * 判断文件是否存在
     *
     * @param httpPath 路径
     * @return true：文件存在，false：文件不存在
     */
    public static Boolean existHttpPath(String httpPath) {
        URL httpurl = null;
        try {
            httpurl = new URL(new URI(httpPath).toASCIIString());
            URLConnection urlConnection = httpurl.openConnection();
            long totalSize = Long.parseLong(urlConnection.getHeaderField("Content-Length"));
            return totalSize > 0;
        } catch (Exception e) {
            log.debug(httpurl + "文件不存在");
            return false;
        }
    }

    public static byte[] downloadFile(String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET,
                httpEntity, byte[].class);
        if (Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
            return response.getBody();
        }
        throw new DataNotFoundException(FileErrorEnum.NOT_FOUND);
    }
}
