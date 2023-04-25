package com.blucharge.puppet.ocpi;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.text.SimpleDateFormat;

@Component
public class UtilityClass {

    public static String DATE_FORMAT_NOW = "yyyy-MM-dd%sHH:mm:ss%s";
    public String randomStringGenerator(@NonNull Calendar cal){
        //Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(String.format(DATE_FORMAT_NOW,"T","Z"));
        return sdf.format(cal.getTime());
    }
}
