package com.example.real_school.twofactor.controller;

import com.example.real_school.common.dto.ApiResponse;
import com.example.real_school.twofactor.dto.*;
import com.example.real_school.twofactor.service.TwoFactorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users/{userId}/two-factor")
@RequiredArgsConstructor
public class TwoFactorController {

    private final TwoFactorService twoFactorService;

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<TwoFactorStatusDto>> getStatus(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(
                "Two-factor status retrieved", twoFactorService.getStatus(userId)));
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    @PostMapping("/enable")
    public ResponseEntity<ApiResponse<Void>> enable(@PathVariable Long userId,
                                                    @Valid @RequestBody EnableTwoFactorRequest request) {
        twoFactorService.sendEnableCode(userId, request);
        return ResponseEntity.ok(ApiResponse.success(
                "Verification code sent. Use /verify to complete setup.", null));
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Void>> verify(@PathVariable Long userId,
                                                    @Valid @RequestBody VerifyTwoFactorRequest request) {
        twoFactorService.verifyAndEnable(userId, request);
        return ResponseEntity.ok(ApiResponse.success("Two-factor authentication enabled", null));
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    @PostMapping("/disable")
    public ResponseEntity<ApiResponse<Void>> disable(@PathVariable Long userId,
                                                     @Valid @RequestBody DisableTwoFactorRequest request) {
        twoFactorService.disable(userId, request);
        return ResponseEntity.ok(ApiResponse.success("Two-factor authentication disabled", null));
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    @PutMapping("/phone")
    public ResponseEntity<ApiResponse<Void>> updatePhone(@PathVariable Long userId,
                                                         @Valid @RequestBody UpdatePhoneRequest request) {
        twoFactorService.updatePhone(userId, request);
        return ResponseEntity.ok(ApiResponse.success("Phone number updated", null));
    }
}