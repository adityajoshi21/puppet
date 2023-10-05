package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.StartTransactionReq;
import com.blucharge.puppet.service.StartTransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.blucharge.puppet.constants.ApplicationConstants.*;


@Service
public class StartTransactionServiceImpl implements StartTransactionService {


    @Override
    public String sendStartTransactionMessage() {

        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));

        StartTransactionReq req = new StartTransactionReq();
        req.setConnectorId(CONNECTOR_ID);
        req.setMeterStart(METER_START_VALUE);
//        req.setReservationId(RESERVATION_ID);
        req.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
        req.setIdTag(ID_TAG_STAGING);
        return  "[2,\""+randomStringValue+"\",\"StartTransaction\"," + new Gson().toJson(req)+"]";

    }
}