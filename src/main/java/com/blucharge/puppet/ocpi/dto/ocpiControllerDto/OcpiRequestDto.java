package com.blucharge.puppet.ocpi.dto.ocpiControllerDto;

import lombok.*;

@Data
public class OcpiRequestDto {
    @NonNull
    private String url;
    @NonNull
    private String token;
}
