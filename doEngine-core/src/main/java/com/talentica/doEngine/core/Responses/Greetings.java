package com.talentica.doEngine.core.Responses;

/**
 * Created by aravindp on 8/6/16.
 */
public class Greetings {

    private String message;

    public boolean isApplicable(String message){
        this.message = message;
        switch (this.message.toLowerCase()){
            case "hi":
            case "hi!":
            case "hi! who r u":
            case "hi! who are u":
            case "hi! who are you":
            case "hi who r u":
            case "hi who are u":
            case "hi who are you":
            case "who r u":
            case "who are u":
            case "who are you":
                return true;
        }
        return false;
    }

    public String getResponse(){

        switch (this.message.toLowerCase()){
            case "hi":
            case "hi!":
                return "{\"status\":600, \"data\": \"Hi! how may I help u?\"}";
            case "hi! who r u":
            case "hi! who are u":
            case "hi! who are you":
            case "hi who r u":
            case "hi who are u":
            case "hi who are you":
            case "who r u":
            case "who are u":
            case "who are you":
                return "{\"status\":600, \"data\": \"I am Atom, what can I do for u?\"}";
        }
        return "Sorry, I didn't get u?";
    }
}
