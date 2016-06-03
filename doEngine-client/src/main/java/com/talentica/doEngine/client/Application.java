package com.talentica.doEngine.client;

import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.TelegramBotAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;

import java.util.HashMap;
import java.util.Map;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by aravindp on 30/5/16.
 */
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.talentica.doEngine.core", "com.talentica.doEngine.client"})
public class Application implements CommandLineRunner {

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

        Map<String,String> temp = new HashMap<String, String>();
        temp.put("abc","123");
        temp.put("bcd","234");
        //publisher.publishIncomingMessage(temp);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
