package com.example.real_school.user.service.impl;

import com.example.real_school.common.exception.BadRequestException;
import com.example.real_school.common.exception.ResourceNotFoundException;
import com.example.real_school.user.dto.UserDto;
import com.example.real_school.user.dto.UserStatusUpdateRequest;
import com.example.real_school.user.entity.Role;
import com.example.real_school.user.entity.User;
import com.example.real_school.user.repository.RoleRepository;
import com.example.real_school.user.repository.UserRepository;
import com.example.real_school.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        Role role = roleRepository.findByName(userDto.getRole())
                .orElseThrow(() -> new BadRequestException("Role not found: " + userDto.getRole()));
        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(role)
                .isActive(userDto.getEnabled() == null || userDto.getEnabled())
                .build();
        return mapToDto(userRepository.save(user));
    } 
    @Override
    public UserDto getUserById(Long id) {
        return mapToDto(findUser(id));
    }
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = findUser(id);
        if (userDto.getFirstName() != null) user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null) user.setLastName(userDto.getLastName());
        if (userDto.getEmail() != null && !userDto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(userDto.getEmail())) {
                throw new BadRequestException("Email is already registered");
            }
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getRole() != null) {
            Role role = roleRepository.findByName(userDto.getRole().toUpperCase())
                    .orElseThrow(() -> new BadRequestException("Role not found: " + userDto.getRole()));
            user.setRole(role);
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }

        return mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findUser(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserDto updateUserStatus(Long id, UserStatusUpdateRequest request) {
        User user = findUser(id);
        Boolean active = request.getActive();
        if (request.getStatus() != null) {
            active = Boolean.parseBoolean(request.getStatus());
        }
        if (active == null) {
            throw new BadRequestException("Active status is required");
        }
        user.setActive(active);
        return mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateUserRole(Long id, String roleName) {
        User user = findUser(id);
        Role role = roleRepository.findByName(roleName.toUpperCase())
                .orElseThrow(() -> new BadRequestException("Role not found: " + roleName));
        user.setRole(role);
        return mapToDto(userRepository.save(user));
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole() != null ? user.getRole().getName() : null)
                .enabled(user.isActive())
                .twoFactorEnabled(user.isTwoFactorEnabled())
                .twoFactorMethod(user.getTwoFactorMethod())
                .createdAt(user.getCreatedAt())
                .build();
    }
}