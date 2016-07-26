package com.talentica.doEngine.client.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.model.Message;
import com.talentica.doEngine.client.telegram.model.Update;
import com.talentica.doEngine.client.telegram.model.request.Keyboard;
import com.talentica.doEngine.client.telegram.model.request.KeyboardButton;
import com.talentica.doEngine.client.telegram.model.request.ReplyKeyboardMarkup;
import com.talentica.doEngine.client.telegram.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aravindp on 23/5/16.
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    TelegramBot telegramBot;

    @RequestMapping(value = "testC", method = RequestMethod.GET)
    @ResponseBody
    public String testC(){
        return "200";
    }

    @RequestMapping(value = "testwebhook", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String testWebhook(@RequestBody Map data){
        System.out.println(data);
        return "250";
    }

    String start = null;
    String end = null;
    String product = null;
    String paymentMode = null;
    String home = null;
    String work = null;
    int seatCount = 0;
    String prev;
    @RequestMapping(value = "webhooks", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String webhook(@RequestBody Object data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(data);
        System.out.println("Received : " + jsonData);
        Update update = mapper.readValue(jsonData, Update.class);

        Message message = update.getMessage();

        Integer userId = message.getFrom().getId();


        //Integer userId = 192591982;
        String text = message.getText();

        if (text.equalsIgnoreCase("clear")) {
            start = null;
            end = null;
            product = null;
            paymentMode = null;
            prev = null;
            return "200";
        }
        if (text.toLowerCase().contains("find cab") || prev != null) {
            prev = text;
            if (text.contains("from") && text.contains("to")){
                start = "picup add";
                end = "dropoff add";
            } else if (text.contains("from")) {
                start = "pickup add";
            } else if (text.contains("to")) {
                end = "dropoff add";
            } else {
                if (!text.toLowerCase().contains("find cab")) {
                    if( start == null) {
                        start = "pick up";
                    } else if (product == null){
                        product = text;
                    } else if (paymentMode == null){
                        paymentMode = text;
                    }
                }
            }

            if (start == null){
                Keyboard keyboard = new ReplyKeyboardMarkup(
                        new KeyboardButton[]{
                                new KeyboardButton("Send current location").requestLocation(true)
                        }
                ).oneTimeKeyboard(true)
                        .resizeKeyboard(true);

                telegramBot.execute(new SendMessage(userId, "plz send the pickup location...").replyMarkup(keyboard));
                return "200";
            }

            if (product == null) {
                Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                        new String[]{"uberGo", "uberX"},
                        new String[]{"uberSUV", "uberPool"})
                        .oneTimeKeyboard(true)   // optional
                        .resizeKeyboard(true)    // optional
                        .selective(true);        // optional
                telegramBot.execute(new SendMessage(userId, "plz select the cab ...").replyMarkup(replyKeyboardMarkup));
                return "200";
            }

            if (paymentMode == null) {
                Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                        new String[]{"cash", "paytm"})
                        .oneTimeKeyboard(true)   // optional
                        .resizeKeyboard(true)    // optional
                        .selective(true);        // optional
                telegramBot.execute(new SendMessage(userId, "plz select the payment option...").replyMarkup(replyKeyboardMarkup));
                return "200";
            }

            telegramBot.execute(new SendMessage(userId, "ur cab has been booked...."));
        }


        return "200";
    }
}
