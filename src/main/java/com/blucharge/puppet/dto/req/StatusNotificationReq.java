package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.enums.ChargePointErrorCode;
import com.blucharge.puppet.enums.ChargePointStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class StatusNotificationReq {
    private int connectorId; //for id=0, the status is for CP main controller
    private Date timestamp = new Date();
    private String vendorId;
    private String info;
    private String vendorErrorCode;
    private ChargePointErrorCode errorCode;
    private ChargePointStatus status;
}
