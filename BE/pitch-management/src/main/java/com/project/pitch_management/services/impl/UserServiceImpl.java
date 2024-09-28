package com.project.pitch_management.services.impl;

import com.project.pitch_management.dto.requests.UserRequest;
import com.project.pitch_management.dto.responses.UserResponse;
import com.project.pitch_management.entities.User;
import com.project.pitch_management.mappers.UserMapper;
import com.project.pitch_management.repositories.UserRepository;
import com.project.pitch_management.services.UserService;
import com.project.pitch_management.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    JwtUtils jwtUtils;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRequest request) {

        String role = "USER";
        String password = passwordEncoder.encode(request.getPassword());
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        // Lưu vào cơ sở dữ liệu
        User user = userMapper.toUser(request);
        user.setRole(role);
        user.setPassword(password);
        user.setCreateAt(localDateTime);
        user.setUpdateAt(localDateTime);
        userRepository.save(user);

        log.info("Registered Success");

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse login(UserRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String generateToken = jwtUtils.generateToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

        log.info("Login Success");

        return UserResponse.builder()
                .role(user.getRole())
                .token(generateToken)
                .fullName(user.getFullName())
                .refreshToken(refreshToken)
                .expirationTime("24Hrs")
                .build();
    }

    @Override
    public List<UserResponse> getUsersAll() {

        log.info("Get All Users Success");

        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(long id) {

        User user = userRepository.findById(id).orElseThrow();

        log.info("Get User By Id Success");

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        return null;
    }

    @Override
    public void deleteUserById(long id) {

    }


}
