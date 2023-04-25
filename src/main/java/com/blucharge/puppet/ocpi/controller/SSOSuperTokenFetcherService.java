package com.blucharge.puppet.ocpi.controller;

public interface SSOSuperTokenFetcherService {
    void resetUserSession();

    String getSystemUser(); //used for schedulers

    String getSsoToken(boolean validate);
}
