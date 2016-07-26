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

    @RequestMapping(value = "/hook")
    @ResponseBody
    public String test(@RequestParam("access_token")String data){
        System.out.println(data);
        return "200";
    }

    @RequestMapping(value = "/callUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String auth(@RequestBody Map data) throws IOException {

        String response = "calling user ..." + data.get("user");

        return response;
    }

    @RequestMapping(value = "/hook", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public int webhook(@RequestBody Object data) throws IOException {

        String response = "calling user ..." + data;

        return 200;
    }

    @RequestMapping(value = "/hook", method = RequestMethod.GET)
    @ResponseBody
    public String getwebhook(@RequestParam("hub.challenge") String hubChallenge) throws IOException {

        String response = "calling user ..." ;

        return hubChallenge;
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    @ResponseBody
    public String getSample(@RequestParam("abc") String abc){
        while (true){
            System.out.println(abc);

            if(abc == "") {
                break;
            }
        }
        return "200";
    }
    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
