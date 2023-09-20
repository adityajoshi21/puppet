package com.blucharge.puppet.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeterValue{
    public String timestamp;
    public SampledValue sampledValue;

}
