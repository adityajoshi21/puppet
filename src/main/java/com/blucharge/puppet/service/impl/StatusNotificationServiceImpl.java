package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.StatusNotificationReq;
import com.blucharge.puppet.service.StatusNotificationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;
import java.util.Random;
@Component
public class StatusNotificationServiceImpl implements StatusNotificationService {
    public String sendStatusNotificationMessage() {
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StatusNotificationReq req = new StatusNotificationReq();
        req.setConnectorId(2);
        req.setStatus("Available");
        req.setErrorCode("NoError");
        req.setInfo("Success");
        req.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
        req.setVendorId("BS43567F");
        req.setVendorErrorCode("0");
        String statusNotificationString = gson.toJson(req);
      return "[2, \""+randomStringValue+"\",\"StatusNotification\","+ statusNotificationString+"]";
    }
}
