package com.omakase.omastay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmaStayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmaStayApplication.class, args);

    }
}
