package com.blucharge.puppet.dto.classes;


import com.blucharge.puppet.dto.enums.*;
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