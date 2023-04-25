package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.dto.classes.IdToken;
import com.blucharge.puppet.service.impl.StartTransactionServiceImpl;
import com.blucharge.puppet.utils.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j

public class StartTransactionReq {

    private  int connectorId;
    private int meterStart;
    private int reservationId;
    @JsonSerialize(using = DateTimeSerializer.class)
    private String timestamp;
    private String idTag;

    //private IdToken idTag;

}
