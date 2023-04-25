package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.dto.enums.ChargePointErrorCode;
import com.blucharge.puppet.dto.enums.ChargePointStatus;
import com.blucharge.puppet.utils.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.JodaTimePermission;
import org.springframework.format.datetime.joda.JodaTimeContext;

import java.util.Date;

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
