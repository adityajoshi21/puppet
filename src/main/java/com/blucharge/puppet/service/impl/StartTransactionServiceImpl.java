package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.StartTransactionReq;
import com.blucharge.puppet.service.StartTransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class StartTransactionServiceImpl implements StartTransactionService {


    @Override
    public String sendStartTransactionMessage() {

        Random rand = new Random();
        String randomStringValue = String.valueOf(rand.nextInt(10000));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StartTransactionReq req = new StartTransactionReq();
        req.setConnectorId(1);
        req.setMeterStart(21);
        req.setReservationId(12);
        req.setTimestamp(DateTime.now(DateTimeZone.UTC).toString()); //object to UTC string deserializer
        req.setIdTag("06d6-4ad8-9d24");
        //req.setIdTag("0ff7-1b47-43e0"); for UAT

        return  "[2,\""+randomStringValue+"\",\"StartTransaction\"," + new Gson().toJson(req)+"]";

    }
}