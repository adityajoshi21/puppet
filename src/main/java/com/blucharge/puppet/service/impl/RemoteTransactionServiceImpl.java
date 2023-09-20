package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.conf.RemoteStartTransactionConf;
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

import static com.blucharge.puppet.constants.ApplicationConstants.TEST_CHARGER;

@Service
public class RemoteTransactionServiceImpl implements RemoteTransactionService {

    private HashMap<String , Boolean> stateOfChargers = new HashMap<>();
    @Override
    public String remoteStartTransaction() {
        stateOfChargers.put(TEST_CHARGER, Boolean.FALSE); //Assigned state set to false at t=0;
        RemoteStartTransactionConf remoteStartTransactionConf = new RemoteStartTransactionConf();

        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        if(Boolean.FALSE.equals(stateOfChargers.get(TEST_CHARGER))) {
            //Change connector's state to assigned
            stateOfChargers.put(TEST_CHARGER, true);
            remoteStartTransactionConf.setStatus(String.valueOf(RemoteStartStopStatus.ACCEPTED));
            return  "[2,\""+randomStringValue+"\",\"RemoteStartTransaction\"," + new Gson().toJson(remoteStartTransactionConf)+"]";
        }
        else
            remoteStartTransactionConf.setStatus(String.valueOf(RemoteStartStopStatus.REJECTED));

        return  "[2,\""+randomStringValue+"\",\"RemoteStartTransaction\"," + new Gson().toJson(remoteStartTransactionConf)+"]";
    }



    @Override
    public String remoteStopTransaction() {
       RemoteStopTransactionConf remoteStopTransactionConf = new RemoteStopTransactionConf();
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        Boolean state = stateOfChargers.get(TEST_CHARGER);
        if(state){
        stateOfChargers.put(TEST_CHARGER, false); //Set assigned state to false
            remoteStopTransactionConf.setStatus(RemoteStartStopStatus.ACCEPTED);
            return  "[2,\""+randomStringValue+"\",\"RemoteStopTransaction\"," + new Gson().toJson(remoteStopTransactionConf)+"]";
        }
        remoteStopTransactionConf.setStatus(RemoteStartStopStatus.REJECTED);
        return  "[2,\""+randomStringValue+"\",\"RemoteStopTransaction\"," + new Gson().toJson(remoteStopTransactionConf)+"]";
    }
}
