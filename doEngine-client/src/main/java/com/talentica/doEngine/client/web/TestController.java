package com.talentica.doEngine.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by aravindp on 23/5/16.
 */
@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(value = "testC", method = RequestMethod.GET)
    @ResponseBody
    public String testC(){
        return "200";
    }

    @RequestMapping(value = "testwebhook", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String testWebhook(@RequestBody Map data){
        System.out.println(data);
        return "250";
    }
}
