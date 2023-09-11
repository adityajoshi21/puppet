package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.dto.classes.MeterValue;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class MeterValuesReq {
    private int connectorId;
    private int transactionId;
    private MeterValue meterValue;


}
