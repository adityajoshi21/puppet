package com.blucharge.puppet.dto.req;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Slf4j

public class RemoteStopTransactionReq {
    private int transactionId;

}
