package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.StopTransactionReq;
import com.blucharge.puppet.service.StopTransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.blucharge.puppet.constants.ApplicationConstants.*;

@Service
public class StopTransactionServiceImpl implements StopTransactionService {
    @Override
    public  String sendStopTransactionMessage(){
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));

        StopTransactionReq req = new StopTransactionReq();
        req.setTransactionId(TRANSACTION_ID);
        req.setIdTag(ID_TAG_STAGING);
        req.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
        req.setMeterStop(METER_STOP_VALUE);
        return  "[2,\""+randomStringValue+"\",\"StopTransaction\"," + new Gson().toJson(req)+"]";
    }
}
