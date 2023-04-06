package com.blucharge.puppet.ocpi.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@JsonPropertyOrder({"data","statusCode","statusMessage","timeStamp"})
public class VersionDataResponse<T> extends VersionResponse {
        T data;
        public VersionDataResponse(T data, Integer statusCode, String statusMessage){
            super(statusCode,statusMessage);
            this.data = data;
        }

        public VersionDataResponse(T data){
            super(1000,"success");
            this.data = data;
        }


}