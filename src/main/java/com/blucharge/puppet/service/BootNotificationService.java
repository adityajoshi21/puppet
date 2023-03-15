package com.blucharge.puppet.service;

import com.blucharge.puppet.dto.req.BootNotificationReq;

public interface BootNotificationService {

    public abstract BootNotificationReq sendBootNotificationMessage(String chargeBoxId);
}
