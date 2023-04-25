package com.blucharge.puppet.service.impl;
import com.blucharge.puppet.dto.req.BootNotificationReq;
import com.blucharge.puppet.service.BootNotificationService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import java.util.Random;
@Component
public class BootNotificationServiceImpl implements BootNotificationService {

    @Override
    public String sendBootNotificationMessage(String chargeBoxId) {
        Random rand = new Random();
        String randomStringValue = String.valueOf(rand.nextInt(10000));
        BootNotificationReq req = new BootNotificationReq();
        req.setChargeBoxSerialNumber(chargeBoxId);
        req.setChargePointModel("HE523853");
        req.setChargePointSerialNumber(chargeBoxId);
        req.setChargePointVendor("BluSmart");
        req.setFirmwareVersion("2.10");
        req.setIccid("9842020537");
        req.setImsi("523853440097");
        req.setMeterSerialNumber("MSN007");
        req.setMeterType("DC");
        return  "[2,\""+randomStringValue+"\",\"BootNotification\"," + new Gson().toJson(req)+"]";
    }
}
