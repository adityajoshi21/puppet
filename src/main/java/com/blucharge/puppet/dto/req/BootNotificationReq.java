package com.blucharge.puppet.dto.req;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class BootNotificationReq {
    private String chargeBoxSerialNumber;
    private String chargePointModel;
    private String chargePointSerialNumber;
    private String chargePointVendor;
    private String iccid;
    private String imsi;
    private String firmwareVersion;
    private String meterSerialNumber;
    private String meterType;

}
