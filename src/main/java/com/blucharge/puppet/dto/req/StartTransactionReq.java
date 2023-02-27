package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.classes.IdToken;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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
    private Date timestamp = new Date();
    private IdToken idTag;
}
