package com.project.pitch_management.mappers;

import com.project.pitch_management.dto.requests.UserRequest;
import com.project.pitch_management.dto.responses.UserResponse;
import com.project.pitch_management.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);
}
