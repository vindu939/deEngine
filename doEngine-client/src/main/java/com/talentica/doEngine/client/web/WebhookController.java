package com.talentica.doEngine.client.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.doEngine.client.telegram.model.Message;
import com.talentica.doEngine.client.telegram.model.MessageEntity;
import com.talentica.doEngine.client.telegram.model.Update;
import com.talentica.doEngine.client.Publisher;
import com.talentica.doEngine.session.SessionManager;
import com.talentica.doEngine.session.SessionMetaData;
import com.talentica.doEngine.session.SessionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by aravindp on 19/5/16.
 */
@RestController
@RequestMapping("/")
public class WebhookController {

    @Autowired
    @Qualifier("IncomingMessagePublisher")
    private Publisher publisher;

    @RequestMapping(value = "webhook", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String webhook(@RequestBody Object data){

        try {
            System.out.println("Received webhook : " + data);
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(data);
            Update update = mapper.readValue(jsonData, Update.class);

            SessionMetaData sessionMetaData = new SessionMetaData();
            // Determine msg from fb or telegram

            Message message = update.getMessage();

            Integer userId = message.getFrom().getId();

            //Integer userId = 192591982;
            String text = message.getText();
            // find the type od session required
            SessionType optedType = SessionType.USER;
            if (message.getEntities() != null && message.getEntities().length > 0){
                MessageEntity messageEntity = message.getEntities()[0];
                if (messageEntity.getType().equalsIgnoreCase("bot_command") &&
                        text.equalsIgnoreCase("/admin")){
                    optedType = SessionType.ADMIN;
                }
            }

            sessionMetaData.setClientType("telegram");
            sessionMetaData.setUserId(userId);
            sessionMetaData.setMessage(text);
            sessionMetaData.setOptedSessionType(optedType);

            // Pass it to session manager
            String sessionId = SessionManager.getSessionID(sessionMetaData);
            // Generate an event
            publisher.publishIncomingMessage(sessionId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "200";
    }

}
