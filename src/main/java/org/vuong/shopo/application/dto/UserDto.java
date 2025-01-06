package org.vuong.shopo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private UUID id;
    private String username;
    private String fullName;
    private String email;
    private Set<UserRole> roles;

    enum UserRole {
        ADMIN, USER, MODERATOR
    }
}
