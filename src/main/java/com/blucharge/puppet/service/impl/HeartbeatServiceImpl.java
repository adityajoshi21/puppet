package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.service.HeartbeatService;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HeartbeatServiceImpl implements HeartbeatService {
    @Override
    public String sendHeartbeatMessage() {
        Random random = new Random();
        Object obj = new Object();
        String randomStringValue = String.valueOf(random.nextInt(10000)); //Generating random int for MessageId
        Gson gson = new Gson();
        return "[2, \""+randomStringValue+"\",\"Heartbeat\","+ gson.toJson(obj)+"]";
    }
}