package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.service.HeartbeatService;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import java.util.Random;

@Component
public class HeartbeatServiceImpl implements HeartbeatService {
    @Override
    public String sendHeartbeatMessage() {
        Random rand = new Random();
        Object obj = new Object();
        String randomStringValue = String.valueOf(rand.nextInt(10000)); //Generating random int for MessageId
        Gson gson = new Gson();
        return "[2, \""+randomStringValue+"\",\"Heartbeat\","+ gson.toJson(obj)+"]";
    }
}