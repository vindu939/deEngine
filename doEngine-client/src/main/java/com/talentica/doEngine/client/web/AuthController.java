package com.talentica.doEngine.client.web;

import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.request.SendMessage;
import com.talentica.doEngine.session.SessionManager;
import com.talentica.doEngine.session.SessionMetaData;
import com.talentica.doEngine.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aravindp on 13/6/16.
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private TelegramBot telegramBot;

    @RequestMapping(value = "authCallback", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void callback(@RequestBody Map data) throws IOException {
        String sessionId = (String) data.get("state");
        String authdata = (String) data.get("auth");
        SessionManager.addUserAuthData(sessionId, authdata);
        SessionObject sessionObject = SessionManager.getSession(sessionId);
        SessionMetaData sessionMetaData = sessionObject.getSessionMetaData();
        telegramBot.execute(new SendMessage(sessionMetaData.getUserId(), "you have been authenticated! How may I help u?"));
    }
}
