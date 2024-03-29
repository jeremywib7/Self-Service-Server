package com.j23.server;

import com.midtrans.Midtrans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
//@EnableWebMvc
@Slf4j
public class ServerApplication  {

  public static void main(String[] args) {

    // Set serverKey to Midtrans global config
    Midtrans.serverKey = "SB-Mid-server-TTNlxI6thXHFeOeHRyj7dj_B";

    // Set value to true if you want Production Environment (accept real transaction).
    Midtrans.isProduction = false;

    SpringApplication.run(ServerApplication.class, args);
    log.info("Server started");
  }

}
