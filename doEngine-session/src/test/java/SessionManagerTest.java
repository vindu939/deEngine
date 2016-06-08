import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.doEngine.session.AuthObject;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by aravindp on 30/5/16.
 */
public class SessionManagerTest {

    @Test
    public void testAuthObj(){
        String authData = "{" +
                    "\"access_token\": \"aa49e025-c4fe-4892-86af-15af2e6b72a2\"," +
                    "\"token_type\": \"bearer\"," +
                    "\"refresh_token\": \"97a9f978-7aad-4af7-9329-78ff2ce9962d\"," +
                    "\"expires_in\": 43199," +
                    "\"scope\": \"read write\"" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AuthObject authObject = objectMapper.readValue(authData, AuthObject.class);
            System.out.println(authObject.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectGen(){
        Map data = new LinkedHashMap<>();
        //data.put("", )
    }
}
