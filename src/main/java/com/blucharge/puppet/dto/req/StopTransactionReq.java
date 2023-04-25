package com.blucharge.puppet.dto.req;

import com.blucharge.puppet.dto.classes.IdToken;
import com.blucharge.puppet.dto.enums.ReasonForStopTransaction;
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
public class StopTransactionReq {

    private String idTag;
    private int meterStop;
    private String timestamp;
    private int transactionId;
    private ReasonForStopTransaction reason; //backend pe transaction data kuch hai

    //MeterValue transactionData; here MeterValue class DT is to be added


}
