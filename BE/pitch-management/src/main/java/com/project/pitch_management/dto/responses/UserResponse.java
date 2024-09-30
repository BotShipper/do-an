package com.project.pitch_management.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    Long id;
    String email;
    String fullName;
    String phone;
    String address;
    String role;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    String token;
    String refreshToken;
    String expirationTime;

}
