package org.vuong.shopo.infrastructure.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vuong.shopo.application.dto.UserDto;
import org.vuong.shopo.infrastructure.integration.HttpClient;
import org.vuong.shopo.infrastructure.integration.HttpRequestBuilder;
import org.vuong.shopo.shared.services.TokenService;

@Service
public class UserService {

    private final static String prefix = "user/";

    private final HttpClient httpClient;
    private final TokenService tokenService;

    public UserService(HttpClient httpClient, TokenService tokenService) {
        this.httpClient = httpClient;
        this.tokenService = tokenService;
    }

    public Boolean checkExistUser(String username) {
        return getUserInfo(username) != null;
    }

    public Boolean checkExistUser(Long id) {
        return getUserInfo(id) != null;
    }

    public UserDto getUserInfo(Long id) {
        String uri = prefix + id;

        ResponseEntity<UserDto> userInfo = new HttpRequestBuilder<UserDto>(httpClient)
                .useCoreApi(uri)
                .useServiceToken(tokenService)
                .get();
        return userInfo.getBody();
    }

    public UserDto getUserInfo(String username) {
        String uri = prefix + username;

        ResponseEntity<UserDto> userInfo = new HttpRequestBuilder<UserDto>(httpClient)
                .useCoreApi(uri)
                .useServiceToken(tokenService)
                .get();
        return userInfo.getBody();
    }
    
    public UserDto getCurrentUserInfo() {
        String uri = prefix + "profile";

        ResponseEntity<UserDto> userInfo = new HttpRequestBuilder<UserDto>(httpClient)
                .useCoreApi(uri)
                .useUserToken()
                .get();
        return userInfo.getBody();
    }
}
