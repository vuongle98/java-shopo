package org.vuong.shopo.shared.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.vuong.shopo.application.dto.UserDto;
import org.vuong.shopo.shared.exceptions.TokenNotFoundException;

import java.util.Objects;

public class Context {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public static UserDto getUser() {
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            throw new TokenNotFoundException("Token not found");
        }

        Authentication context = SecurityContextHolder.getContext().getAuthentication();

        if (!(context.getPrincipal() instanceof UserDto)) {
            throw new TokenNotFoundException("Token not found");
        }

        return (UserDto) context.getPrincipal();
    }

    public static String getUserToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            throw new TokenNotFoundException("Token from user not found");
        }

        HttpServletRequest request = attributes.getRequest();

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length()); // Trả về token từ request
        }

        throw new TokenNotFoundException("Token from user not found");
    }
}