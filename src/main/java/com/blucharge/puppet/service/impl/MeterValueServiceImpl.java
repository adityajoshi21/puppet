package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.conf.RemoteStartTransactionConf;
import com.blucharge.puppet.dto.req.MeterValuesReq;
import com.blucharge.puppet.service.MeterValueService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MeterValueServiceImpl implements MeterValueService {


    @Override
    public String sendMeterValueMessage() {
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        MeterValuesReq req = new MeterValuesReq();
        req.setConnectorId(1);
        req.setTransactionId(1);
        //req.setMeterValue();
        return "[2,\""+randomStringValue+"\",\"MeterValue\"," + new Gson().toJson(req)+"]";
    }
}
