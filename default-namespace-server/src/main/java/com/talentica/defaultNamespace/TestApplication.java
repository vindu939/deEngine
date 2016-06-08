package com.talentica.defaultNamespace;

import com.talentica.defaultNamespace.test.TestA;
import com.talentica.defaultNamespace.test.TestC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aravindp on 25/5/16.
 */
@Configuration
@SpringBootApplication
public class TestApplication implements CommandLineRunner{

    @Bean
    public String abc(){
        return "This is First Test";
    }

    @Bean
    public String bcd(){
        return "This is Second Test";
    }

    @Bean
    public TestA test1(String abc){
        return new TestA(abc);
    }

    @Bean
    public TestA test2(String bcd){
        return new TestA(bcd);
    }

    @Bean
    public TestC myTest(){
        return new TestC();
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(TestApplication.class, args);
    }
}
