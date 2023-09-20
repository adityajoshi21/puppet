package com.blucharge.puppet.service.impl;
import com.blucharge.puppet.dto.req.BootNotificationReq;
import com.blucharge.puppet.service.BootNotificationService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import java.util.Random;

import static com.blucharge.puppet.constants.ApplicationConstants.BOOT_NOTIFICATION_PARAMS;

@Component
public class BootNotificationServiceImpl implements BootNotificationService {

    @Override
    public String sendBootNotificationMessage(String chargeBoxId) {
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        BootNotificationReq req = new BootNotificationReq();
        req.setChargeBoxSerialNumber(chargeBoxId);
        req.setChargePointModel(BOOT_NOTIFICATION_PARAMS[0]);
        req.setChargePointSerialNumber(chargeBoxId);
        req.setChargePointVendor(BOOT_NOTIFICATION_PARAMS[1]);
        req.setFirmwareVersion(BOOT_NOTIFICATION_PARAMS[2]);
        req.setIccid(BOOT_NOTIFICATION_PARAMS[3]);
        req.setImsi(BOOT_NOTIFICATION_PARAMS[4]);
        req.setMeterSerialNumber(BOOT_NOTIFICATION_PARAMS[5]);
        req.setMeterType(BOOT_NOTIFICATION_PARAMS[6]);
        return  "[2,\""+randomStringValue+"\",\"BootNotification\"," + new Gson().toJson(req)+"]";
    }
}
