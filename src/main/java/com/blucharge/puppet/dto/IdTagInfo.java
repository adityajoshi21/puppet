package com.blucharge.puppet.dto;

import com.blucharge.puppet.dto.enums.AuthorizationStatus;
import org.joda.time.DateTime;


public class IdTagInfo {
    private DateTime expiryDate= new DateTime();
    private String parentIdTag;
    private AuthorizationStatus status;

}
