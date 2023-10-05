package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.conf.RemoteStartTransactionConf;
import com.blucharge.puppet.dto.conf.RemoteStopTransactionConf;
import com.blucharge.puppet.dto.enums.ChargePointStatus;
import com.blucharge.puppet.dto.enums.RemoteStartStopStatus;
import com.blucharge.puppet.service.RemoteTransactionService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

import static com.blucharge.puppet.constants.ApplicationConstants.TEST_CHARGER;

@Service
public class RemoteTransactionServiceImpl implements RemoteTransactionService {

    private HashMap<String , Boolean> stateOfChargers = new HashMap<>();
    @Override
    public String remoteStartTransaction( String messageId) {
        stateOfChargers.put(TEST_CHARGER, Boolean.FALSE); //Assigned state set to false at t=0;
        RemoteStartTransactionConf remoteStartTransactionConf = new RemoteStartTransactionConf();

        if(Boolean.FALSE.equals(stateOfChargers.get(TEST_CHARGER))) {
            //Change connector's state to assigned
            stateOfChargers.put(TEST_CHARGER, true);
            remoteStartTransactionConf.setStatus(String.valueOf(RemoteStartStopStatus.Accepted));
            return  "[3,\""+messageId+"\"," + new Gson().toJson(remoteStartTransactionConf)+"]";
        }
        else
            remoteStartTransactionConf.setStatus(String.valueOf(RemoteStartStopStatus.Rejected));

        return  "[3,\""+messageId+"\"," + new Gson().toJson(remoteStartTransactionConf)+"]";
    }



    @Override
    public String remoteStopTransaction(String messageId) {
       RemoteStopTransactionConf remoteStopTransactionConf = new RemoteStopTransactionConf();
        Boolean state = stateOfChargers.get(TEST_CHARGER);
        if(state){
        stateOfChargers.put(TEST_CHARGER, false); //Set assigned state to false
            remoteStopTransactionConf.setStatus(RemoteStartStopStatus.Accepted);
            return  "[3,\"" +messageId+"\"," + new Gson().toJson(remoteStopTransactionConf)+"]";
        }
        remoteStopTransactionConf.setStatus(RemoteStartStopStatus.Rejected);
        return  "[3,\"" +messageId+"\"," + new Gson().toJson(remoteStopTransactionConf)+"]";
    }
}
