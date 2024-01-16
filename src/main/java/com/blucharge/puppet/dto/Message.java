package com.blucharge.puppet.dto;

import com.blucharge.puppet.dto.enums.MessageType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private MessageType type;
    private String messageId;
    private String messageName;
    private String data;
}
