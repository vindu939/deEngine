package com.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aravindp on 13/6/16.
 */
@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(value = "Callback", method = RequestMethod.GET)
    public String callBack(@RequestParam("state")String state, @RequestParam("code")String code){




        return "200";
    }
}
