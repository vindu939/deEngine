package com.test;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;

/**
 * Created by aravindp on 3/6/16.
 */
@SpringBootApplication
@RestController
public class Application implements CommandLineRunner{

    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String auth(@RequestBody Map data) throws IOException {
        String username = (String) data.get("client_id");
        String password = (String) data.get("client_secret");
        String encoding = Base64.getEncoder().encodeToString((username+":"+password).getBytes());
        System.out.println(encoding);

        String auth = username + ":" + password;
        //byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
        //String authHeader = "Basic " + new String(encodedAuth);

        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://localhost:8080/oauth/token");
        postMethod.addRequestHeader("Authorization", "basic "+encoding);
        StringRequestEntity requestEntity = new StringRequestEntity(
                "\"username\"=\"vindu525@gmail.com\"&\"password\"=\"12345\"&\"grant_type\"=\"password\"",
                "application/x-www-form-urlencoded", "UTF-8");
        postMethod.setRequestEntity(requestEntity);
        int status = httpClient.executeMethod(postMethod);
        String responseBody = postMethod.getResponseBodyAsString();
        System.out.println(responseBody);
        return "200";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String test(@RequestBody Map data) throws IOException {
        String token = (String) data.get("token");
        HttpClient httpClient = new HttpClient();
        GetMethod postMethod = new GetMethod("http://localhost:8080/user");
        postMethod.addRequestHeader("Authorization", "bearer "+token);
        int status = httpClient.executeMethod(postMethod);
        String responseBody = postMethod.getResponseBodyAsString();
        System.out.println(responseBody);
        return "200";
    }

    @RequestMapping(value = "/authTest", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String authtest(@RequestBody Map data) throws IOException {
        String username = (String) data.get("client_id");
        String password = (String) data.get("client_secret");

        HttpClient httpClient = new HttpClient();
        httpClient.getState().setCredentials(
                new AuthScope("localhost",8080),
                new UsernamePasswordCredentials(username, password)
        );
        PostMethod postMethod = new PostMethod("http://localhost:8080/oauth/token");
        postMethod.setDoAuthentication(true);
        //postMethod.addRequestHeader("Authorization", "basic "+encoding);
        StringRequestEntity requestEntity = new StringRequestEntity(
                "\"username\"=\"vindu525@gmail.com\"&\"password\"=\"12345\"&\"grant_type\"=\"password\"",
                "application/x-www-form-urlencoded", "UTF-8");
        postMethod.setRequestEntity(requestEntity);
        int status = httpClient.executeMethod(postMethod);
        String responseBody = postMethod.getResponseBodyAsString();
        System.out.println(responseBody);
        return "200";
    }
    @Override
    public void run(String... strings) throws Exception {

    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
