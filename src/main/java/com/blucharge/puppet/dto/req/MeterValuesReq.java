package com.blucharge.puppet.dto.req;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Slf4j
public class MeterValuesReq {

    private int connectorId;
    private int transactionId;
    // public MeterValue meterValue; need to clarify from DB


}
