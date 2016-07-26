package com.talentica.uber.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.talentica.doEngine.session.SessionManager;
import com.talentica.doEngine.session.SessionObject;
import com.talentica.uber.AuthObject;
import com.talentica.uber.Utils;
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
    public String callback(@RequestParam(value = "state", required = false) String state, @RequestParam("code") String code){

        try {
            //SessionObject sessionObject = SessionManager.getSession(state);
            String userId = state.split("_")[1];//String.valueOf(sessionObject.getSessionMetaData().getUserId());

            Credential credential = Utils.getCredentials(code, userId);

            AuthObject authObject = new AuthObject();
            authObject.setAccessToken(userId);
            authObject.setRefreshToken(credential.getRefreshToken());
            authObject.setTokenType("Bearer");

            ObjectMapper mapper = new ObjectMapper();
            String JSON_RESPONSE = mapper.writeValueAsString(authObject);

            //SessionManager.addUserAuthData(state, JSON_RESPONSE);

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("http://localhost:8080/authCallback");
            post.setHeader("Content-Type", "application/json");

            Map<String, String> obj = new HashMap<>();
            obj.put("state", state.split("_")[0]);
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

        return "Please close this window!!";
    }

    @RequestMapping(value = "authUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getAuthUrl(){
        return "https://login.uber.com/oauth/v2/authorize?client_id=" + clientId + "&response_type=code&scope=profile+places+ride_widgets+history_lite+history+request+all_trips+request_receipt";
    }

}
