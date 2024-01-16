package com.blucharge.puppet.dto.req;

import com.blucharge.puppet.utils.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class StatusNotificationReq {
    private int connectorId; //for id=0, the status is for CP main controller
    @JsonSerialize(using = DateTimeSerializer.class)
    private String timestamp;
    private String vendorId;
    private String info;
    private String vendorErrorCode;
    private String errorCode;
    private String status;
}
