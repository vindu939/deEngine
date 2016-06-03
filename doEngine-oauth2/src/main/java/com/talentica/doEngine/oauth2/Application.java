package com.talentica.doEngine.oauth2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aravindp on 24/5/16.
 */
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner{

    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
