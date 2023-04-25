package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.conf.RemoteStartTransactionConf;
import com.blucharge.puppet.service.RemoteStartTxnService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RemoteStartTxnServiceImpl implements RemoteStartTxnService {


    @Override
    public String sendRemoteStartMessage() {
        Random rand = new Random();
        String randomStringValue = String.valueOf(rand.nextInt(10000));
        RemoteStartTransactionConf req = new RemoteStartTransactionConf();
        req.setStatus("Accepted");

        return "[2,\""+randomStringValue+"\",\"RemoteStartTransaction\"," + new Gson().toJson(req)+"]";
    }
}
