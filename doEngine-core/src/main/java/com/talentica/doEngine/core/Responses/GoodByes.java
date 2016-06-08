package com.talentica.doEngine.core.Responses;

/**
 * Created by aravindp on 8/6/16.
 */
public class GoodByes {
    private String message;

    public boolean isApplicable(String message){
        this.message = message;
        switch (this.message.toLowerCase()) {
            case "exit":
            case "bye":
            case "cya":
            case "c u later":
            case "good bye":
            case "goodbye":
            case "ba bye":
            case "got to go":
            case "talk to u later":
            case "ttl":
                return true;
        }
        return false;
    }

    public String getResponse(){

        switch (this.message.toLowerCase()){
            case "exit":
            case "bye":
            case "cya":
            case "c u later":
            case "good bye":
            case "goodbye":
            case "ba bye":
            case "got to go":
            case "talk to u later":
            case "ttl":
                return "{\"status\":600, \"data\": \"Ok bye! have a good day\"}";
        }
        return "Sorry, I didn't get u?";
    }
}
