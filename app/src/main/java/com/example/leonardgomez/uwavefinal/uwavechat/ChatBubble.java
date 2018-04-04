package com.example.leonardgomez.uwavefinal.uwavechat;

import com.example.leonardgomez.uwavefinal.R;
import com.example.leonardgomez.uwavefinal.uwavechat.*;

import java.util.Date;

/**
 * Created by chrisoung on 3/30/18.
 */

public class ChatBubble {

    private String content;
    private boolean myMessage;


    public ChatBubble(String content, boolean myMessage) {
        this.content = content;
        this.myMessage = myMessage;

    }

    public String getContent() {
        String content = this.content;
        return content;
    }

    public boolean myMessage() {
        boolean myMessage = this.myMessage;
        return myMessage;
    }
}
