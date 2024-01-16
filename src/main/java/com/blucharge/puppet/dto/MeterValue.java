package com.blucharge.puppet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeterValue{
    public String timestamp;
    private List<SampledValue> sampledValue;
}
