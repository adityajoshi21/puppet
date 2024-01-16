package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.StatusNotificationReq;
import com.blucharge.puppet.service.StatusNotificationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.blucharge.puppet.constants.ApplicationConstants.CONNECTOR_ID;
import static com.blucharge.puppet.constants.ApplicationConstants.STATUS_NOTIFICATION_PARAMS;

@Service
public class StatusNotificationServiceImpl implements StatusNotificationService {
    public String sendStatusNotificationMessage() {
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StatusNotificationReq req = new StatusNotificationReq();
        req.setConnectorId(CONNECTOR_ID);
        req.setStatus(STATUS_NOTIFICATION_PARAMS[0]);
        req.setErrorCode(STATUS_NOTIFICATION_PARAMS[1]);
        req.setInfo(STATUS_NOTIFICATION_PARAMS[2]);
        req.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
        req.setVendorId(STATUS_NOTIFICATION_PARAMS[3]);
        req.setVendorErrorCode(STATUS_NOTIFICATION_PARAMS[4]);
        String statusNotificationString = gson.toJson(req);
      return "[2, \""+randomStringValue+"\",\"StatusNotification\","+ statusNotificationString+"]";
    }
}
