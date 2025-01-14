package org.vuong.shopo.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vuong.shopo.infrastructure.integration.HttpClient;
import org.vuong.shopo.infrastructure.integration.HttpRequestBuilder;
import org.vuong.shopo.shared.services.TokenService;
import org.vuong.shopo.shared.utils.ResponseUtil;

@Controller
@RequestMapping("/test")
public class TestController {

    private final HttpClient httpClient;
    private final TokenService tokenService;

    public TestController(HttpClient httpClient, TokenService tokenService) {
        this.httpClient = httpClient;
        this.tokenService = tokenService;
    }


    @GetMapping
    public ResponseEntity<Object> test() {

        ResponseEntity<Object> users = new HttpRequestBuilder<>(httpClient)
                .url("http://localhost:8000/core/user/")
                .responseType(Object.class)
                .useUserToken()
                .get();
        System.out.println(users.getBody());
        return new ResponseEntity<>(ResponseUtil.success(users.getBody(), "Get data success"), HttpStatus.OK);
    }
}
