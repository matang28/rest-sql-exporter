package com.matang28.restsqlexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args){

        //Run the spring application:
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
