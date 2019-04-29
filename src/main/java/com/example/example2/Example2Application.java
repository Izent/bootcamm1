package com.example.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Example2Application {

  public static void main(String[] args) {
    SpringApplication.run(Example2Application.class, args);
  }

}
