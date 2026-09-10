package com.example.real_school.twofactor.dto;

import com.example.real_school.user.entity.TwoFactorMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TwoFactorStatusDto {
    private boolean twoFactorEnabled;
    private TwoFactorMethod method;
    private String maskedEmail;
    private String maskedPhone;
}