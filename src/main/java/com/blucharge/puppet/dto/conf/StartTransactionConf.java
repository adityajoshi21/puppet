package com.blucharge.puppet.dto.conf;
import com.blucharge.puppet.classes.IdTagInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Slf4j

public class StartTransactionConf {
private int transactionId;
private IdTagInfo idTagInfo;
}
