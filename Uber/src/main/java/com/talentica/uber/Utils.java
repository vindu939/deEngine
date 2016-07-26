package com.talentica.uber;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.auth.OAuth2Credentials;
import com.uber.sdk.rides.client.CredentialsSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.UserProfile;
import com.uber.sdk.rides.client.services.RidesService;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aravindp on 15/6/16.
 */
public class Utils {

    private static String clientId;
    private static String clientSecret;
    private static String redirectUrl;

    static {
        clientId = PropertiesHandler.getProperty("clientId");
        clientSecret = PropertiesHandler.getProperty("clientSecret");
        redirectUrl = PropertiesHandler.getProperty("redirectUrl");
    }

    public static Credential getCredentials(String code, String userId) throws IOException {
        OAuth2Credentials oAuth2Credentials = createOAuth2Credentials();
        Credential credential = oAuth2Credentials.loadCredential(userId);

        if (credential == null || credential.getAccessToken() == null) {
            credential = oAuth2Credentials.authenticate(code, userId);
        }

        return credential;
    }

    public static RidesService getService(String userId) throws IOException {

        OAuth2Credentials oAuth2Credentials = Utils.createOAuth2Credentials();
        Credential credential = oAuth2Credentials.loadCredential(userId);
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUrl)
                .build();

        if (credential == null){
            return null;
        }

        CredentialsSession session = new CredentialsSession(config, credential);
        UberRidesApi uberRidesApi = UberRidesApi.with(session).build();

        RidesService service = uberRidesApi.createService();

        return service;
    }

    public static OAuth2Credentials createOAuth2Credentials() throws IOException {

        // Store the users OAuth2 credentials in their home directory.
        File credentialDirectory =
                new File(System.getProperty("user.home") + File.separator + "uber_credentials");
        credentialDirectory.setReadable(true, true);
        credentialDirectory.setWritable(true, true);
        // If you'd like to store them in memory or in a DB, any DataStoreFactory can be used.
        AbstractDataStoreFactory dataStoreFactory = new FileDataStoreFactory(credentialDirectory);

        List<Scope> scopes = new ArrayList<>();
        scopes.add(Scope.REQUEST);
        scopes.add(Scope.REQUEST_RECEIPT);
        scopes.add(Scope.ALL_TRIPS);
        scopes.add(Scope.PROFILE);
        scopes.add(Scope.PLACES);
        scopes.add(Scope.RIDE_WIDGETS);
        scopes.add(Scope.HISTORY);
        scopes.add(Scope.HISTORY_LITE);
        // Build an OAuth2Credentials object with your secrets.
        return new OAuth2Credentials.Builder()
                .setCredentialDataStoreFactory(dataStoreFactory)
                .setClientSecrets(clientId, clientSecret)
                .setRedirectUri(redirectUrl)
                .setScopes(scopes)
                .build();
    }
}
