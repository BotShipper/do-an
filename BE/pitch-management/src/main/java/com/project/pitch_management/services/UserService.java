package com.project.pitch_management.services;

import com.project.pitch_management.dto.requests.UserRequest;
import com.project.pitch_management.dto.responses.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse register(UserRequest request);

    UserResponse login(UserRequest request);

    List<UserResponse> getUsersAll();

    UserResponse getUserById(long id);

    UserResponse updateUser(UserRequest request);

    void deleteUserById(long id);

}
