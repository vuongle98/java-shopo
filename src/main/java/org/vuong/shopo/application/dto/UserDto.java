package org.vuong.shopo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private Set<UserRole> roles;

    enum UserRole {
        ADMIN, USER, MODERATOR
    }

    public boolean isAdmin() {
        return roles.contains(UserRole.ADMIN);
    }
}
