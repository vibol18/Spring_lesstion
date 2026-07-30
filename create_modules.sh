#!/bin/bash
set -e

BASE="src/main/java/com/example/real_school"

# =============================================
# AUTH & USER MODULES
# =============================================

# --- User Entities & Repositories ---
mkdir -p "$BASE/user/entity" "$BASE/user/repository" "$BASE/user/service/impl" "$BASE/user/controller" "$BASE/user/dto"

cat > "$BASE/user/entity/Role.java" << 'EOF'
package com.example.real_school.user.entity;

import com.example.real_school.common.enums.Role as RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleEnum name;
}
EOF

cat > "$BASE/user/entity/User.java" << 'EOF'
package com.example.real_school.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
EOF

cat > "$BASE/user/repository/UserRepository.java" << 'EOF'
package com.example.real_school.user.repository;

import com.example.real_school.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
EOF

cat > "$BASE/user/repository/RoleRepository.java" << 'EOF'
package com.example.real_school.user.repository;

import com.example.real_school.common.enums.Role as RoleEnum;
import com.example.real_school.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
EOF

# --- User Service & Controller ---
cat > "$BASE/user/service/UserService.java" << 'EOF'
package com.example.real_school.user.service;

import com.example.real_school.user.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
EOF

cat > "$BASE/user/service/impl/UserServiceImpl.java" << 'EOF'
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
EOF

cat > "$BASE/user/controller/UserController.java" << 'EOF'
package com.example.real_school.user.controller;

import com.example.real_school.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
EOF

cat > "$BASE/user/dto/UserDto.java" << 'EOF'
package com.example.real_school.user.dto;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private Set<String> roles;
}
EOF

# --- Auth Service & Controller ---
mkdir -p "$BASE/auth/controller" "$BASE/auth/service/impl" "$BASE/auth/dto"

cat > "$BASE/auth/dto/LoginRequest.java" << 'EOF'
package com.example.real_school.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
EOF

cat > "$BASE/auth/dto/LoginResponse.java" << 'EOF'
package com.example.real_school.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tokenType = "Bearer";
}
EOF

cat > "$BASE/auth/dto/RegisterRequest.java" << 'EOF'
package com.example.real_school.auth.dto;

import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;
}
EOF

cat > "$BASE/auth/service/AuthService.java" << 'EOF'
package com.example.real_school.auth.service;

import com.example.real_school.auth.dto.LoginRequest;
import com.example.real_school.auth.dto.LoginResponse;
import com.example.real_school.auth.dto.RegisterRequest;

public interface AuthService {
    LoginResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
}
EOF

cat > "$BASE/auth/service/impl/AuthServiceImpl.java" << 'EOF'
package com.example.real_school.auth.service.impl;

import com.example.real_school.auth.dto.LoginRequest;
import com.example.real_school.auth.dto.LoginResponse;
import com.example.real_school.auth.dto.RegisterRequest;
import com.example.real_school.auth.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) { return null; }
    @Override
    public void registerUser(RegisterRequest registerRequest) {}
}
EOF

cat > "$BASE/auth/controller/AuthController.java" << 'EOF'
package com.example.real_school.auth.controller;

import com.example.real_school.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
}
EOF

# =============================================
# ACADEMIC MODULE
# =============================================
mkdir -p "$BASE/academic/entity" "$BASE/academic/repository" "$BASE/academic/service/impl" "$BASE/academic/controller" "$BASE/academic/dto"

cat > "$BASE/academic/entity/AcademicYear.java" << 'EOF'
package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "academic_years")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrent;
}
EOF

cat > "$BASE/academic/entity/SchoolClass.java" << 'EOF'
package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
}
EOF

cat > "$BASE/academic/entity/Section.java" << 'EOF'
package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;
}
EOF

cat > "$BASE/academic/entity/Subject.java" << 'EOF'
package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
}
EOF

cat > "$BASE/academic/entity/ClassSubjectTeacher.java" << 'EOF'
package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class_subject_teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSubjectTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Long teacherId; // References teacher
}
EOF

cat > "$BASE/academic/entity/Enrollment.java" << 'EOF'
package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // References student

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    private LocalDate enrollmentDate;
    private String rollNumber;
}
EOF

# --- Academic Repositories ---
cat > "$BASE/academic/repository/AcademicYearRepository.java" << 'EOF'
package com.example.real_school.academic.repository;

import com.example.real_school.academic.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
}
EOF

cat > "$BASE/academic/repository/SchoolClassRepository.java" << 'EOF'
package com.example.real_school.academic.repository;

import com.example.real_school.academic.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}
EOF

cat > "$BASE/academic/repository/SectionRepository.java" << 'EOF'
package com.example.real_school.academic.repository;

import com.example.real_school.academic.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
EOF

cat > "$BASE/academic/repository/SubjectRepository.java" << 'EOF'
package com.example.real_school.academic.repository;

import com.example.real_school.academic.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
EOF

cat > "$BASE/academic/repository/EnrollmentRepository.java" << 'EOF'
package com.example.real_school.academic.repository;

import com.example.real_school.academic.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
EOF

# --- Academic Services ---
cat > "$BASE/academic/service/AcademicYearService.java" << 'EOF'
package com.example.real_school.academic.service;

public interface AcademicYearService {}
EOF
cat > "$BASE/academic/service/ClassService.java" << 'EOF'
package com.example.real_school.academic.service;

public interface ClassService {}
EOF
cat > "$BASE/academic/service/SectionService.java" << 'EOF'
package com.example.real_school.academic.service;

public interface SectionService {}
EOF
cat > "$BASE/academic/service/SubjectService.java" << 'EOF'
package com.example.real_school.academic.service;

public interface SubjectService {}
EOF
cat > "$BASE/academic/service/EnrollmentService.java" << 'EOF'
package com.example.real_school.academic.service;

public interface EnrollmentService {}
EOF

cat > "$BASE/academic/service/impl/AcademicYearServiceImpl.java" << 'EOF'
package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.AcademicYearService;
import org.springframework.stereotype.Service;

@Service
public class AcademicYearServiceImpl implements AcademicYearService {}
EOF

cat > "$BASE/academic/service/impl/ClassServiceImpl.java" << 'EOF'
package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {}
EOF

cat > "$BASE/academic/service/impl/SectionServiceImpl.java" << 'EOF'
package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.SectionService;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService {}
EOF

cat > "$BASE/academic/service/impl/SubjectServiceImpl.java" << 'EOF'
package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {}
EOF

cat > "$BASE/academic/service/impl/EnrollmentServiceImpl.java" << 'EOF'
package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.EnrollmentService;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {}
EOF

# --- Academic Controllers & DTOs ---
cat > "$BASE/academic/controller/ClassController.java" << 'EOF'
package com.example.real_school.academic.controller;

import com.example.real_school.academic.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;
}
EOF

cat > "$BASE/academic/controller/SectionController.java" << 'EOF'
package com.example.real_school.academic.controller;

import com.example.real_school.academic.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {
    private final SectionService sectionService;
}
EOF

cat > "$BASE/academic/controller/SubjectController.java" << 'EOF'
package com.example.real_school.academic.controller;

import com.example.real_school.academic.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
}
EOF

cat > "$BASE/academic/dto/ClassDto.java" << 'EOF'
package com.example.real_school.academic.dto;

import lombok.*;

@Data
public class ClassDto {
    private Long id;
    private String name;
    private String code;
}
EOF

cat > "$BASE/academic/dto/SectionDto.java" << 'EOF'
package com.example.real_school.academic.dto;

import lombok.*;

@Data
public class SectionDto {
    private Long id;
    private String name;
    private Long classId;
}
EOF

cat > "$BASE/academic/dto/SubjectDto.java" << 'EOF'
package com.example.real_school.academic.dto;

import lombok.*;

@Data
public class SubjectDto {
    private Long id;
    private String name;
    private String code;
}
EOF

echo "✅ Auth, User, and Academic modules done"
