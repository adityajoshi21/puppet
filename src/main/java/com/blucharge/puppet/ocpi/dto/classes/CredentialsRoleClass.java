package com.blucharge.puppet.ocpi.dto.classes;

import org.springframework.lang.NonNull;
import com.blucharge.puppet.ocpi.dto.enums.CredentialRoles;

public class CredentialsRoleClass {

    @NonNull
    private CredentialRoles role;
    @NonNull
    private BusinessDetailsClass businessDetails;
    @NonNull
    private String partyId;
    @NonNull
    private String countryCode;
}
