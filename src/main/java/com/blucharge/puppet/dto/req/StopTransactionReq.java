package com.blucharge.puppet.dto.req;

import com.blucharge.puppet.dto.enums.ReasonForStopTransaction;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class StopTransactionReq {

    private String idTag;
    private Integer meterStop;
    private String timestamp;
    private Integer transactionId;
    private ReasonForStopTransaction reason; //backend pe transaction data kuch hai

    //MeterValue transactionData; is an optional field


}
