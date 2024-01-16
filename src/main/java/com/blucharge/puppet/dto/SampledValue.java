package com.blucharge.puppet.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampledValue {
    private String value;
    private String context;
    private String format;
    private String phase;
    private String measurand;
    private  String location;
    private String unit;
}