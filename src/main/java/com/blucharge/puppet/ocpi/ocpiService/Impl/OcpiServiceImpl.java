package com.blucharge.puppet.ocpi.ocpiService.Impl;

import com.blucharge.core.services.RestService;
import com.blucharge.puppet.ocpi.dto.classes.VersionClass;
//import com.blucharge.puppet.ocpi.dto.response.VersionDataResponseClass;
import com.blucharge.puppet.ocpi.dto.response.VersionDataResponse;
import com.google.gson.reflect.TypeToken;
import com.blucharge.puppet.ocpi.dto.ocpiControllerDto.OcpiRequestDto;
import com.blucharge.puppet.ocpi.ocpiService.OcpiService;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;
//import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class OcpiServiceImpl implements OcpiService {

//    public static final String versionEndpoint = "https://ocpp.uat.blucgn.com/blucharge/ocpi/cpo/versions";
    @Autowired
    private RestService restService;


    @Override
    public VersionDataResponse<List<VersionClass>> getVersionsService(OcpiRequestDto ocpiRequestDto) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Authorization",ocpiRequestDto.getToken());
            Type t = new TypeToken<VersionDataResponse<List<VersionClass>>>(){}.getType();
            String responseObj = restService.get(ocpiRequestDto.getUrl(),headers,null, String.class,true,false);
            System.out.println(responseObj);

            VersionDataResponse<List<VersionClass>> response = new Gson().fromJson(responseObj,t);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
