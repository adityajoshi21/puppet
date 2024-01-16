package com.blucharge.puppet.dto.conf;
import java.util.Date;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class HeartbeatConf {
    @NonNull
    Date currentTime = new Date();
}

