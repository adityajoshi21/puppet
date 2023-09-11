package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.AuthorizeReq;
import com.blucharge.puppet.service.AuthoriseService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AuthoriseServiceImpl implements AuthoriseService {

    @Override
    public String sendAuthoriseMessage() {
        AuthorizeReq req = new AuthorizeReq();
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
//        req.setIdTag("06d6-4ad8-9d24"); //For Local DB
        req.setIdTag("0d11-4a26-92c3");  //for UAT
        return " [2, \""+randomStringValue+"\",\"Authorize\","+new Gson().toJson(req)+"]";
    }
}
