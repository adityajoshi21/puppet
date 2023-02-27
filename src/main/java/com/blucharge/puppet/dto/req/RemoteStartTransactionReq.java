package com.blucharge.puppet.dto.req;

import com.blucharge.puppet.classes.IdToken;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class RemoteStartTransactionReq {
    private int connectorId;
    private IdToken idTag;

    //chargingProfile class
}
