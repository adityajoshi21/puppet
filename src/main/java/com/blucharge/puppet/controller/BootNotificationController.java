package com.blucharge.puppet.controller;
import com.blucharge.puppet.dto.OutputMessage;
import com.blucharge.puppet.dto.Message;
import com.blucharge.puppet.dto.req.BootNotificationReq;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class BootNotificationController {

            @MessageMapping("/chat")
            @SendTo("/topic/messages")
            public OutputMessage send(final Message message) throws Exception {

                final String time = new SimpleDateFormat("HH:mm").format(new Date());
                return new OutputMessage(message.getFrom(), message.getText(), time);

            }
}



