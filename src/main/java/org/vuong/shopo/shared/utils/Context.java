package org.vuong.shopo.shared.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vuong.shopo.application.dto.UserDto;

import java.util.Objects;

public class Context {

    public static UserDto getUser() {
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            return null;
        }

        Authentication context = SecurityContextHolder.getContext().getAuthentication();

        if (!(context.getPrincipal() instanceof UserDto)) {
            return null;
        }

        return (UserDto) context.getPrincipal();
    }

    public static String getToken() {
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            return null;
        }

        Authentication context = SecurityContextHolder.getContext().getAuthentication();

        return String.valueOf(context.getPrincipal());
    }
}