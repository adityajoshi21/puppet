package com.blucharge.puppet.dto.classes;


import com.blucharge.puppet.dto.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampledValue {
    protected String value;
    protected ReadingContext context;
    protected ValueFormat format;
    protected Measurand measurand;
    protected Phase phase;
    protected Location location;
    protected String unit;

}