package com.blucharge.puppet.dto.req;

import com.blucharge.puppet.classes.IdToken;
import com.blucharge.puppet.enums.ReasonForStopTransaction;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Slf4j
public class StopTransactionReq {

    private IdToken idTag;
    private int meterStop;
    private Date timestamp = new Date();
    private int transactionId;
    private ReasonForStopTransaction reason;

    //MeterValue transactionData; here MeterValue class DT is to be added


}
