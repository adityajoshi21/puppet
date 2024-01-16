package com.blucharge.puppet.dto.conf;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j

public class RemoteStartTransactionConf {
    private String status;

}
