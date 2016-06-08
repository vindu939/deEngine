package com.test;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aravindp on 3/6/16.
 */
@SpringBootApplication
@RestController
public class Application implements CommandLineRunner{

    @RequestMapping(value = "/callUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String auth(@RequestBody Map data) throws IOException {

        String response = "calling user ..." + data.get("user");

        return response;
    }

    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
