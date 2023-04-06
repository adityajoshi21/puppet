package com.blucharge.puppet.ocpi.ocpiService;

import com.blucharge.puppet.ocpi.dto.classes.VersionClass;
//import com.blucharge.puppet.ocpi.dto.response.VersionDataResponseClass;
import com.blucharge.puppet.ocpi.dto.response.VersionDataResponse;
import com.blucharge.puppet.ocpi.dto.ocpiControllerDto.OcpiRequestDto;

import java.util.List;

public interface OcpiService {

   VersionDataResponse<List<VersionClass>> getVersionsService(OcpiRequestDto ocpiRequestDto);
}
