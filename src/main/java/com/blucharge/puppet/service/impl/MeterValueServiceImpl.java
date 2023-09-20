package com.blucharge.puppet.service.impl;

import com.blucharge.puppet.dto.req.MeterValuesReq;
import com.blucharge.puppet.service.MeterValueService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MeterValueServiceImpl implements MeterValueService {


    @Override
    public String sendMeterValueMessage() {
        Random random = new Random();
        String randomStringValue = String.valueOf(random.nextInt(10000));
        MeterValuesReq req = new MeterValuesReq();
        req.setConnectorId(1);
        req.setTransactionId(1);

//        MeterValue meterValue = new MeterValue();
//        meterValue.setTimestamp(DateTime.now(DateTimeZone.UTC).toString());
//        SampledValue sampledValue = new SampledValue();
//        sampledValue.setValue("98");
//        sampledValue.setContext(ReadingContext.valueOf(ReadingContext.TRANSACTION_END.name()));
//        sampledValue.setFormat(ValueFormat.valueOf(ValueFormat.RAW.name()));
//        sampledValue.setMeasurand(Measurand.valueOf(Measurand.SOC.name()));
//        sampledValue.setPhase(Phase.valueOf(Phase.L_1.name()));
//        sampledValue.setLocation(Location.valueOf(Location.OUTLET.name()));
//        meterValue.setSampledValue(sampledValue);
//
//        req.setMeterValue(meterValue);
        return "[2,\""+randomStringValue+"\",\"MeterValues\"," + new Gson().toJson(req)+"]";
    }
}
