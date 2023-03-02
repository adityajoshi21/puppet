package com.blucharge.puppet.dto.conf;
import com.blucharge.puppet.dto.enums.RegistrationStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class BootNotificationConf {
    private Date currentTime = new Date();
    private int heartbeatInterval;
    private RegistrationStatus status;

}
