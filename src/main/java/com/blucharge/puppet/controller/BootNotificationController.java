package com.blucharge.puppet.controller;

import com.blucharge.puppet.dto.conf.BootNotificationConf;
import com.blucharge.puppet.dto.req.BootNotificationReq;
import org.springframework.stereotype.Controller;

@Controller
public class BootNotificationController{
    public BootNotificationConf response (BootNotificationReq req) throws InterruptedException {

        return new BootNotificationConf();
    }
}