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
        String randomStringValue = String.valueOf(new Random(10000));
        req.setIdTag("06d6-4ad8-9d24");
        //req.setIdTag("0ff7-1b47-43e0");  for UAT
        return " [2, \""+randomStringValue+"\",\"Authorize\","+new Gson().toJson(req)+"]";
    }
}
