package com.omakase.omastay;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class OmaStayApplication {

    public static void main(String[] args) {

        SpringApplication.run(OmaStayApplication.class, args);
    }
}
