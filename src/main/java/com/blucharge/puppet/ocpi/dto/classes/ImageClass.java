package com.blucharge.puppet.ocpi.dto.classes;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.blucharge.puppet.ocpi.dto.enums.ImageCategory;
import org.springframework.lang.NonNull;

@RequiredArgsConstructor
@Data
public class ImageClass {
    @NonNull
    private String  url;
    private String thumbnail;
    @NonNull
    private ImageCategory category;
    @NonNull
    private String type;
    private Integer width;
    private Integer height;
}
