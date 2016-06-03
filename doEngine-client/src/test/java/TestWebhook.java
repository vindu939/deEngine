import com.talentica.doEngine.client.Publisher;
import com.talentica.doEngine.session.SessionManager;
import com.talentica.doEngine.session.SessionMetaData;
import com.talentica.doEngine.session.SessionType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aravindp on 31/5/16.
 */
public class TestWebhook {

    @Autowired
    Publisher publisher;
    @Test
    public void test(){
        Integer userId = 192591982;
        String message = "create object";
        String optedType = "admin";
        SessionMetaData sessionMetaData = new SessionMetaData();
        sessionMetaData.setClientType("telegram");
        sessionMetaData.setUserId(userId);
        sessionMetaData.setMessage(message);
        sessionMetaData.setOptedSessionType(SessionType.ADMIN);

        // Pass it to session manager
        String sessionId = SessionManager.getSessionID(sessionMetaData);
        // Generate an event
        try {
            publisher.publishIncomingMessage(sessionId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
