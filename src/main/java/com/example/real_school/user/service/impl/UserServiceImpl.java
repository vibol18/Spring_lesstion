package com.example.real_school.user.service.impl;

import com.example.real_school.user.dto.UserDto;
import com.example.real_school.user.dto.UserStatusUpdateRequest;
import com.example.real_school.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto createUser(UserDto userDto) {
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        return new UserDto();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return Collections.emptyList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
    }

    @Override
    public UserDto updateUserStatus(Long id, UserStatusUpdateRequest request) {
        UserDto dto = new UserDto();
        return dto;
    }
}
