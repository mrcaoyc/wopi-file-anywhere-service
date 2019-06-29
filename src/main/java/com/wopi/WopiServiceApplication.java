package com.wopi;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CaoYongCheng
 */
@SpringBootApplication
@EnableSwagger2Doc
@ComponentScan("com")
public class WopiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WopiServiceApplication.class, args);
    }
}
