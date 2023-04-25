package com.blucharge.puppet.ocpi.dto.classes;


import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CredentialsClass {
    @NonNull
    private String token;
    @NonNull
    private String url;
    @NonNull
    private List<CredentialsRoleClass> roles;
}