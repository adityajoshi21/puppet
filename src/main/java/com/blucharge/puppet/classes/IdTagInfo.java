package com.blucharge.puppet.classes;

import com.blucharge.mockcharger.enums.AuthorizationStatus;

import java.util.Date;

public class IdTagInfo {
    private Date expiryDate= new Date();
    private IdToken parentIdTag;
    private AuthorizationStatus status;

}
