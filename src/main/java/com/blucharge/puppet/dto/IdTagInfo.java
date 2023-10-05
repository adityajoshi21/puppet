package com.blucharge.puppet.dto.classes;

import com.blucharge.puppet.dto.enums.AuthorizationStatus;

import java.util.Date;

public class IdTagInfo {
    private Date expiryDate= new Date();
    private IdToken parentIdTag;
    private AuthorizationStatus status;

}
