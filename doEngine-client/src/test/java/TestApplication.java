import com.talentica.doEngine.client.Publisher;
import com.talentica.doEngine.client.Receiver;
import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.TelegramBotAdapter;
import com.talentica.doEngine.session.SessionManager;
import com.talentica.doEngine.session.SessionMetaData;
import com.talentica.doEngine.session.SessionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by aravindp on 31/5/16.
 */
@SpringBootApplication
@Configuration
@ComponentScan({"com.talentica.doEngine.session", "com.talentica.doEngine.core", "com.talentica.doEngine.client"})
public class TestApplication implements CommandLineRunner{

    @Value("${telegram.bot.token}")
    String TELEGRAM_BOT_TOKEN;

    @Bean
    TelegramBot telegramBot(){
        return TelegramBotAdapter.build(TELEGRAM_BOT_TOKEN);
    }

    @Bean
    Environment env(){
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    EventBus eventBus(Environment env){
        return EventBus.create(env, Environment.THREAD_POOL);
    }

    @Autowired
    EventBus eventBus;

    @Autowired
    @Qualifier("IncomingMessageReceiver")
    com.talentica.doEngine.core.Receiver incomingMessageReceiver;

    @Autowired
    @Qualifier("OutgoingMessageReceiver")
    Receiver outgoingMessageReceiver;

    @Autowired
    @Qualifier("IncomingMessagePublisher")
    Publisher publisher;

    @Override
    public void run(String... strings) throws Exception {
        eventBus.on($("IncomingMessageQueue"), incomingMessageReceiver);
        eventBus.on($("OutgoingMessageQueue"), outgoingMessageReceiver);
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
        publisher.publishIncomingMessage(sessionId);
    }

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(TestApplication.class, args);
       /* Integer userId = 192591982;
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
        Publisher publisher = (Publisher) ctx.getBean("IncomingMessagePublisher");
        try {
            publisher.publishIncomingMessage(sessionId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
