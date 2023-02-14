package com.blucharge.puppet.dto.conf;
import com.blucharge.puppet.enums.RegistrationStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Slf4j
public class BootNotificationConf {

    private Date currentTime = new Date();
    private int heartbeatInterval;
    private RegistrationStatus status;
}
