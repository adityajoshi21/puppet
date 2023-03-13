package com.blucharge.puppet.service.impl;
import com.blucharge.puppet.dto.req.BootNotificationReq;
import com.blucharge.puppet.service.BootNotificationService;
import org.springframework.stereotype.Service;

@Service
public class BootNotificationImpl implements BootNotificationService {

    @Override
    public void sendBootNotificationMessage(BootNotificationReq req) {
        req.setChargeBoxSerialNumber("0123");
        req.setChargePointModel("HE513161");
        req.setChargePointSerialNumber("BSCN-GGN004");
        req.setChargePointVendor("Exicom");
        req.setFirmwareVersion("2.10");
        req.setIccid("9842020537");
        req.setImsi("523853440097");
        req.setMeterSerialNumber("MSN007");
        req.setMeterType("DC");
    }
}
