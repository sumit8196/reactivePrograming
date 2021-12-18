package com.rp.sec11SinkTelecast.assignment;

import com.rp.coureutil.Util;

public class SlackDemo {
    public static void main(String[] args) {
        SlackRoom2 slackRoom=new SlackRoom2("Reactor");
        SlackMember sumit=new SlackMember("Sumit");
        SlackMember sam=new SlackMember("Sam");
        SlackMember jack=new SlackMember("Jack");
        slackRoom.joinRoom(sumit);
        slackRoom.joinRoom(sam);

        sumit.says("Welcome All");
        Util.sleep(2);
        sam.says("Good Morning");
        Util.sleep(2);

        Util.sleep(2);
        slackRoom.joinRoom(jack);
        jack.says("Nice to meet you");
    }
}
