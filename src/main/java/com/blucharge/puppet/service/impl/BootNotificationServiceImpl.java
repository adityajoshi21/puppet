package com.blucharge.puppet.service.impl;
import com.blucharge.puppet.dto.req.BootNotificationReq;
import com.blucharge.puppet.service.BootNotificationService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class BootNotificationServiceImpl implements BootNotificationService {

    @Override
    public String sendBootNotificationMessage(String chargeBoxId) {
        BootNotificationReq req = new BootNotificationReq();
        req.setChargeBoxSerialNumber(chargeBoxId);
        req.setChargePointModel("HE513161");
        req.setChargePointSerialNumber(chargeBoxId);
        req.setChargePointVendor("Exicom");
        req.setFirmwareVersion("2.10");
        req.setIccid("9842020537");
        req.setImsi("523853440097");
        req.setMeterSerialNumber("MSN007");
        req.setMeterType("DC");
        return  new Gson().toJson(req);
    }
}
