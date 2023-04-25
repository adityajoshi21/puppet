package com.blucharge.puppet.dto.req;
import com.blucharge.puppet.dto.classes.IdToken;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j

public class AuthorizeReq {
@NonNull
private String idTag;
}
