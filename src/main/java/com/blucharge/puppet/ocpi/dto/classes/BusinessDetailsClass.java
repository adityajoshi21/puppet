package com.blucharge.puppet.ocpi.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class BusinessDetailsClass {
    @NonNull
    private String name;
    private String website;
    private ImageClass logo;
}
