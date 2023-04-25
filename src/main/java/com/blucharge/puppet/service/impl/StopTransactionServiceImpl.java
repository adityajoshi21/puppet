package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.StopTransactionReq;
import com.blucharge.puppet.service.StopTransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class StopTransactionServiceImpl implements StopTransactionService {
    @Override
    public  String sendStopTransactionMessage(){
        Random rand = new Random();
        String randomStringValue = String.valueOf(rand.nextInt(10000));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StopTransactionReq req = new StopTransactionReq();
        req.setTransactionId(1);
        req.setIdTag("06d6-4ad8-9d24");
        // req.setIdTag("0ff7-1b47-43e0")  //for UAT
        req.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
        req.setMeterStop(28);
        return  "[2,\""+randomStringValue+"\",\"StopTransaction\"," + new Gson().toJson(req)+"]";
    }
}
