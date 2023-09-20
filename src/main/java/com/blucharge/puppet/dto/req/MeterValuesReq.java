package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.dto.classes.MeterValue;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MeterValuesReq {
    private int connectorId;
    private int transactionId;
    private MeterValue meterValue;
}
