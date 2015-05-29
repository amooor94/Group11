package com.example.amor.easydrive;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Amor on 2015-05-21.
 */
@ParseClassName("Message")
public class Message extends ParseObject {
    // Ensure that your subclass has a public default constructor
    public Message() {
        super();
    }

    // Add a constructor that contains core properties
    public Message(String body) {
        super();
        setMessage(body);
    }

    // Use getString and others to access fields
    public String message() {
        return getString("message");
    }

    // Use put to modify field values
    public void setMessage(String value) {
        put("message", value);
    }

}
