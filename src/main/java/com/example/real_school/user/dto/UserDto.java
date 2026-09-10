package com.example.real_school.user.dto;

import com.example.real_school.user.entity.TwoFactorMethod;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String role;
    private Boolean enabled;
    private Boolean twoFactorEnabled;
    private TwoFactorMethod twoFactorMethod;
    private LocalDateTime createdAt;
}