package com.blucharge.puppet.dto.req;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class RemoteStartTransactionReq {
    private Integer connectorId;
    private String idTag;

    //chargingProfile class
}
