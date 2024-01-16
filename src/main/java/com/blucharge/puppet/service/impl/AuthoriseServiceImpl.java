package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.AuthorizeReq;
import com.blucharge.puppet.service.AuthoriseService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.blucharge.puppet.constants.ApplicationConstants.ID_TAG_STAGING;

@Service
public class AuthoriseServiceImpl implements AuthoriseService {

    @Override
    public String sendAuthoriseMessage() {
        AuthorizeReq req = new AuthorizeReq();
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        req.setIdTag(ID_TAG_STAGING);  //for UAT
        return " [2, \""+randomStringValue+"\",\"Authorize\","+new Gson().toJson(req)+"]";
    }
}
