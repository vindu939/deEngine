package com.talentica.uber;

import com.google.api.client.util.store.AbstractDataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.auth.OAuth2Credentials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aravindp on 15/6/16.
 */
public class Utils {

    public static OAuth2Credentials createOAuth2Credentials(String clientId, String clientSecret, String redirectUrl) throws IOException {

        // Store the users OAuth2 credentials in their home directory.
        File credentialDirectory =
                new File(System.getProperty("user.home") + File.separator + ".uber_credentials");
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
