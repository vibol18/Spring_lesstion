package com.example.real_school.user.service.impl;

import com.example.real_school.user.dto.UserDto;
import com.example.real_school.user.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto createUser(UserDto userDto) { return null; }
    @Override
    public UserDto getUserById(Long id) { return null; }
    @Override
    public List<UserDto> getAllUsers() { return null; }
    @Override
    public UserDto updateUser(Long id, UserDto userDto) { return null; }
    @Override
    public void deleteUser(Long id) {}
}
