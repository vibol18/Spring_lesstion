package com.example.real_school.twofactor.dto;

import com.example.real_school.user.entity.TwoFactorMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnableTwoFactorRequest {

    @NotNull(message = "Method is required")
    private TwoFactorMethod method;

    // Required only when method = PHONE (if the user has no saved phone yet).
    private String phone;
}