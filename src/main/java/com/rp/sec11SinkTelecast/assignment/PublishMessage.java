package com.rp.sec11SinkTelecast.assignment;

import com.rp.coureutil.Util;

public class PublishMessage {
    public static void main(String[] args) {
        SlackRoom slackRoom=new SlackRoom("Offshore Meeting");
        Member sumit=new Member("Sumit");
        Member jack=new Member("Jack");
        slackRoom
                .joinRoom(sumit);
        slackRoom.sendMessage(sumit,"Hii everyone");
        Member sam=new Member("sam");
        slackRoom.joinRoom(sam);
        slackRoom.joinRoom(jack);
        slackRoom.sendMessage(sam,"nice to see you all");
        slackRoom.sendMessage(sumit,"welcome sam");

        Util.sleep(20);
    }
}
