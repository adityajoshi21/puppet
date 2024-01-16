package com.blucharge.puppet.dto.conf;

import com.blucharge.puppet.dto.IdTagInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j

public class StopTransactionConf {
    private IdTagInfo idTagInfo;
}
