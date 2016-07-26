package com.talentica.uber.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aravindp on 15/6/16.
 */
@RestController
@RequestMapping("/")
public class TestWebhook {

    @RequestMapping(value = "status", method = RequestMethod.POST, consumes = "application/json")
    public String status(@RequestBody Object data){
        System.out.println(data);
        return "200";
    }
}
