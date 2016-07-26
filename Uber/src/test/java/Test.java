import com.google.api.client.auth.oauth2.Credential;
import com.talentica.uber.Utils;
import com.uber.sdk.rides.auth.OAuth2Credentials;
import com.uber.sdk.rides.client.CredentialsSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.UserProfile;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by aravindp on 15/6/16.
 */
public class Test {

    @org.junit.Test
    public void testUber() throws IOException {
        int userId = 12345;
        String clientId = "i3sCAaHlmrt40Qyuuj3WUKEK3Zaokosv";
        String clientSecret = "rLDntaVWKb9_KwgJL9eIgcbsjPQmAw0ZLvT4isY6";
        String redirectUrl = "http://localhost:8080/Callback";
        OAuth2Credentials oAuth2Credentials = Utils.createOAuth2Credentials();
        Credential credential = oAuth2Credentials.loadCredential(Integer.toString(userId));
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUrl)
                .build();

        if (credential == null){
            //return "user not authenticated";
        }

        CredentialsSession session = new CredentialsSession(config, credential);
        UberRidesApi uberRidesApi = UberRidesApi.with(session).build();

        RidesService service = uberRidesApi.createService();

        Response<UserProfile> response1 = service.getUserProfile().execute();
        System.out.println(response1.body().getEmail());
    }

    @org.junit.Test
    public void testHttp(){
        /*HttpClient client = HttpClientBuilder.create().build();
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
                    + response.getStatusLine().getStatusCode());*/
    }
}
