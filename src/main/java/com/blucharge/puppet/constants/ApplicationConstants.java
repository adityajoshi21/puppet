package com.blucharge.puppet.constants;

import java.util.Random;

public class ApplicationConstants {

    static Random random = new Random();
    public static final String TEST_CHARGER = "chargepoint0234";
    public static final String STAGING_BASE_URL = "ws://charge.tracking.staging.blucharge.net/blucharge/connect/";
    public static final String LOCAL_BASE_URL = "ws://localhost:8082/blucharge/connect/";
    public static  final String OCPP_VERSION = "ocpp1.6";
    public static final String ID_TAG_STAGING = "0d11-4a26-92c3";
    public static final String BOOT_NOTIFICATION_PARAMS[] = { "HE523853", "BluSmart", "2.13", "9842020537", "523853440097", "MSN007", "DC" };
    public static final String STATUS_NOTIFICATION_PARAMS[] = { "Available", "NoError", "Success", "BS43567F8", "0" };
    public static final Integer METER_START_VALUE = random.nextInt(25) + 1; //takes random value b/w 1-25
    public static final Integer METER_STOP_VALUE = 98;
    public static final Integer RESERVATION_ID = 1001;
    public static final Integer TRANSACTION_ID = 1;
    public static final Integer CONNECTOR_ID = random.nextInt(3) + 1; //takes random value b/w 1-3

}