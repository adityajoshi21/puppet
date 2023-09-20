package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.conf.RemoteStopTransactionConf;
import com.blucharge.puppet.dto.enums.ChargePointStatus;
import com.blucharge.puppet.dto.enums.RemoteStartStopStatus;
import com.blucharge.puppet.service.RemoteTransactionService;
import com.blucharge.puppet.service.StatusNotificationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

import static com.blucharge.puppet.constants.ApplicationConstants.TEST_CHARGER;

@Service
public class RemoteTransactionServiceImpl implements RemoteTransactionService {

    private HashMap<String , Boolean> stateOfChargers = new HashMap<>();
    @Override
    public String remoteStartTransaction() {
        stateOfChargers.put(TEST_CHARGER, Boolean.FALSE); //Assigned state set to false at t=0;
        RemoteStopTransactionConf remoteStopTransactionConf = new RemoteStopTransactionConf();

        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        if(Boolean.FALSE.equals(stateOfChargers.get(TEST_CHARGER))) {
            //Change connector's state to assigned
            stateOfChargers.put(TEST_CHARGER, true);
            remoteStopTransactionConf.setStatus(RemoteStartStopStatus.ACCEPTED);
            return  "[2,\""+randomStringValue+"\",\"RemoteStartTransaction\"," + new Gson().toJson(remoteStopTransactionConf)+"]";
        }
        else
            remoteStopTransactionConf.setStatus(RemoteStartStopStatus.REJECTED);

        return  "[2,\""+randomStringValue+"\",\"RemoteStartTransaction\"," + new Gson().toJson(remoteStopTransactionConf)+"]";
    }



    @Override
    public String remoteStopTransaction() {
       Boolean state = stateOfChargers.get(TEST_CHARGER);
       RemoteStopTransactionConf remoteStopTransactionConf = new RemoteStopTransactionConf();
        if(state){
        stateOfChargers.put(TEST_CHARGER, false); //Set assigned state to false
            remoteStopTransactionConf.setStatus(RemoteStartStopStatus.ACCEPTED);
        }
        remoteStopTransactionConf.setStatus(RemoteStartStopStatus.REJECTED);
        return   remoteStopTransactionConf.getStatus().toString();
    }
}
