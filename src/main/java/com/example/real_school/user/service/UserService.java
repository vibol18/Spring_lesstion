package com.example.real_school.user.service;

import com.example.real_school.user.dto.UserDto;
import com.example.real_school.user.dto.UserStatusUpdateRequest;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    UserDto updateUserStatus(Long id, UserStatusUpdateRequest request);
    UserDto updateUserRole(Long id, String roleName);
}