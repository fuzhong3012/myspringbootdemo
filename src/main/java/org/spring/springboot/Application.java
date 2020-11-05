package org.spring.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.spring.springboot.mapper")
public class Application {
    public static void main(String[] args) {
        //启动了springboot程序，启动spring容器，启动内嵌的tomcat
        SpringApplication.run(Application.class, args);
    }
}
