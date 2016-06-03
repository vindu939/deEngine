package com.talentica.doEngine.oauth2.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aravindp on 27/5/16.
 */
@RestController
@RequestMapping("/")
public class TestController {

    //@PreAuthorize("#oauth2.hasScope('read')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "listdata", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getData(){
        List<String> data = new ArrayList<>();
        data.add("Aravind");
        data.add("Avinash");
        data.add("Abhisht");
        data.add("Rishabh");
        data.add("Vishal");
        return data;
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }

}
