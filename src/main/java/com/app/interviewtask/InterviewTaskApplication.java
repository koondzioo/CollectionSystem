package com.app.interviewtask;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterviewTaskApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(InterviewTaskApplication.class);
        builder.headless(false).run(args);
    }

    @Bean
    public DriverConfig driverConfig(){
        return new DriverConfig();
    }
}
