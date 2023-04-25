package com.blucharge.puppet.ocpi.controller;

import com.blucharge.core.dto.ResponseDto;
import com.blucharge.puppet.ocpi.dto.classes.VersionClass;
import com.blucharge.puppet.ocpi.dto.response.VersionDataResponse;
import com.blucharge.puppet.ocpi.dto.ocpiControllerDto.OcpiRequestDto;
//import com.blucharge.puppet.ocpi.dto.response.VersionDataResponseClass;
import com.blucharge.puppet.ocpi.ocpiService.OcpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/puppet/info")
public class OcpiController {
    @Autowired
    private OcpiService ocpiService;

    @PostMapping(value = "/version")
    public ResponseDto<VersionDataResponse<List<VersionClass>>> getVersions(@RequestBody OcpiRequestDto ocpiRequestDto) {
        //return (ocpiRequestDto);
        //VersionDataResponseClass<List<VersionResponseClass>> abc = getVersionsService()

        return new ResponseDto<>("SUCCESS",200,"REQUEST SUCCESSFUL",ocpiService.getVersionsService(ocpiRequestDto));
        //return new VersionDataResponseClass<>();
    }
}
