package com.talentica.defaultNamespace.test;

/**
 * Created by aravindp on 8/6/16.
 */
public class Greetings {

    private String message;

    public boolean isApplicable(String message){
        this.message = message;
        switch (this.message.toLowerCase()){
            case "Hi":
            case "Hi!":
            case "Hi! who r u":
            case "Hi! who are u":
            case "Hi! who are you":
            case "Hi who r u":
            case "Hi who are u":
            case "Hi who are you":
            case "who r u":
            case "who are u":
            case "who are you":
                return true;
        }
        return false;
    }

    public String getResponse(){

        switch (this.message.toLowerCase()){
            case "Hi":
            case "Hi!":
                return "Hi! how may I help u?";
            case "Hi! who r u":
            case "Hi! who are u":
            case "Hi! who are you":
            case "Hi who r u":
            case "Hi who are u":
            case "Hi who are you":
            case "who r u":
            case "who are u":
            case "who are you":
                return "I am Atom, what can I do for u";
        }
        return "Sorry, I didn't get u?";
    }
}
