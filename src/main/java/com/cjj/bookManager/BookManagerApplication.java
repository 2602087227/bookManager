package com.cjj.bookManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: bookManagerApplication
 * Package: com.cjj.bookManager
 * Description:
 *
 * @Author: 陈
 * @Create: 2022/12/27 - 14:48
 * @Version: v1.0
 */
@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableTransactionManagement
public class BookManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManagerApplication.class,args);
    }
}
