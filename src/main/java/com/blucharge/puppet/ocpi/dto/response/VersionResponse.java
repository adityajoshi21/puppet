package com.blucharge.puppet.ocpi.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
//import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import org.joda.time.DateTime;

@NoArgsConstructor
@Getter
@Setter
public class VersionResponse {
    @NonNull
    private Integer status_code;
    private String status_message;
    private String timestamp;

    public VersionResponse(Integer statusCode, String statusMessage) {
        this.status_code = statusCode;
        this.status_message = statusMessage;
        this.timestamp = DateTime.now().toString();
    }
}