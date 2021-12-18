package com.rp.sec11SinkTelecast.assignment;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SlackMessage {
    private static final String FORMAT="[%s->%s] : %s";
    private String sender;
    private String receiver;
    private String message;

    public String toString(){
        return String.format(FORMAT,this.sender,this.receiver,this.message);
    }
}
