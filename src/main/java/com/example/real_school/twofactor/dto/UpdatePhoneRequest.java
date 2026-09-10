package com.example.real_school.twofactor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePhoneRequest {

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+\\d][\\d\\s\\-()]{6,19}$", message = "Phone number is not valid")
    private String phone;
}