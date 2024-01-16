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

public class StartTransactionReq {

    private  Integer connectorId;
    private Integer meterStart;
    private Integer reservationId;
    @JsonSerialize(using = DateTimeSerializer.class)
    private String timestamp;
    private String idTag;
}
