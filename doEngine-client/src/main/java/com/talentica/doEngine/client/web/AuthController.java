package com.talentica.doEngine.client.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.uber.sdk.rides.auth.OAuth2Credentials;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aravindp on 13/6/16.
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${redirectUrl}")
    private String redirectUrl;

    @RequestMapping(value = "Callback", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public void callback(@RequestParam(value = "state", required = false) String state, @RequestParam("code") String code){

        OAuth2Credentials oAuth2Credentials = new OAuth2Credentials.Builder()
                .setRedirectUri(redirectUrl)
                .setClientSecrets(clientId, clientSecret)
                .build();

        AuthorizationCodeFlow authorizationCodeFlow = oAuth2Credentials.getAuthorizationCodeFlow();
        AuthorizationCodeTokenRequest tokenRequest = authorizationCodeFlow.newTokenRequest(code);

        TokenResponse tokenResponse = null;
        try {
            tokenResponse = tokenRequest
                    .setRedirectUri(redirectUrl)
                    .execute();

            ObjectMapper mapper = new ObjectMapper();
            String JSON_RESPONSE = mapper.writeValueAsString(tokenResponse);
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("http://localhost:8080/authCallback");
            post.setHeader("Content-Type", "application/json");

            Map<String, String> obj = new HashMap<>();
            obj.put("state", state);
            obj.put("auth", JSON_RESPONSE);

            JSON_RESPONSE = mapper.writeValueAsString(obj);

            StringEntity entity = new StringEntity(JSON_RESPONSE, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "authUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthUrl(){
        return "https://login.uber.com/oauth/v2/authorize?client_id=" + clientId + "&response_type=code";
    }

}
