package com.talentica.defaultNamespace.web.objects;

import com.talentica.defaultNamespace.test.TestA;
import com.talentica.defaultNamespace.test.TestC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by aravindp on 23/5/16.
 */
@Controller
@RequestMapping("/")
public class TestController {

    @Autowired
    TestC testC;

    @RequestMapping(value = "test", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String test(@RequestBody SkeletonRequest skeletonRequest){
        return skeletonRequest.getAction() + "," + skeletonRequest.getLabel() + "," +skeletonRequest.getObject();
    }

    @RequestMapping(value = "testC", method = RequestMethod.GET)
    @ResponseBody
    public String testC(){
        System.out.println(testC.getVal());
        System.out.println(testC.getVal());
        return "200";
    }
}
