package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.classes.MeterValue;
import com.blucharge.puppet.dto.classes.SampledValue;
import com.blucharge.puppet.dto.enums.*;
import com.blucharge.puppet.dto.req.MeterValuesReq;
import com.blucharge.puppet.service.MeterValueService;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MeterValueServiceImpl implements MeterValueService {


    @Override
    public String sendMeterValueMessage() {
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        MeterValuesReq req = new MeterValuesReq();
        req.setConnectorId(1);
        req.setTransactionId(1);
        List<MeterValue> meterValues = new ArrayList<>();
        MeterValue meterValue = new MeterValue();
        meterValue.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
        List<SampledValue> sampledValues = new ArrayList<>();
        SampledValue sampledValue1 = new SampledValue("18", ReadingContext.TRANSACTION_BEGIN.value(), ValueFormat.RAW.value(), Phase.L_1.value(), Measurand.SOC.value(), Location.EV.value(), "Percent" );
        SampledValue sampledValue2 = new SampledValue("14577", ReadingContext.SAMPLE_PERIODIC.value(), ValueFormat.RAW.value(), Phase.L_1.value(), Measurand.ENERGY_ACTIVE_IMPORT_REGISTER.value(), Location.OUTLET.value(), "wH" );
        SampledValue sampledValue3 = new SampledValue("14588", ReadingContext.SAMPLE_PERIODIC.value(), ValueFormat.RAW.value(), Phase.L_1.value(), Measurand.ENERGY_ACTIVE_IMPORT_REGISTER.value(), Location.OUTLET.value(), "wH" );
        SampledValue sampledValue4 = new SampledValue("14599", ReadingContext.SAMPLE_PERIODIC.value(), ValueFormat.RAW.value(), Phase.L_1.value(),  Measurand.ENERGY_ACTIVE_IMPORT_REGISTER.value(), Location.OUTLET.value(), "wH" );
        SampledValue sampledValue5 = new SampledValue( "98", ReadingContext.TRANSACTION_END.value(), ValueFormat.RAW.value(), Phase.L_1.value(),  Measurand.SOC.value(), Location.EV.toString(), "Percent" );
        sampledValues.add(sampledValue1);
        sampledValues.add(sampledValue2);
        sampledValues.add(sampledValue3);
        sampledValues.add(sampledValue4);
        sampledValues.add(sampledValue5);
        meterValue.setSampledValue(sampledValues);
        meterValues.add(meterValue);
        req.setMeterValue(meterValues);
        return "[2,\""+randomStringValue+"\",\"MeterValues\"," + new Gson().toJson(req)+"]";
    }
}
