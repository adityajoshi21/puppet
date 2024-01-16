package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.dto.MeterValue;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MeterValuesReq {
    private int connectorId;
    private int transactionId;
    private List<MeterValue> meterValue;
}
