package org.vuong.shopo.infrastructure.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.vuong.shopo.shared.services.TokenService;
import org.vuong.shopo.shared.utils.Context;

import java.util.Map;

public class HttpRequestBuilder<T> {

    @Value("${shopo.base-core-api}")
    String baseCoreApi;

    @Value("${shopo.base-core-host}")
    String baseCoreHost;

    @Value("${shopo.base-core-port}")
    String baseCorePort;

    private final HttpClient httpClient;
    private final RequestContext requestContext = new RequestContext();
    private String url;
    private HttpMethod method;
    private Map<String, ?> queryParams;
    private Object body;
    private Class<T> responseType;

    public HttpRequestBuilder(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpRequestBuilder<T> url(String url) {
        this.url = url;
        return this;
    }

    public HttpRequestBuilder<T> useCoreApi(String api) {
        this.url = this.baseCoreHost + ":" + this.baseCorePort + "/" + api;
        return this;
    }

    public HttpRequestBuilder<T> method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder<T> token(String token) {
        this.requestContext.setToken(token);
        return this;
    }

    public HttpRequestBuilder<T> headers(Map<String, String> headers) {
        this.requestContext.setAdditionalHeaders(headers);
        return this;
    }

    public HttpRequestBuilder<T> bodyType(MediaType bodyType) {
        this.requestContext.setBodyType(bodyType);
        return this;
    }

    public HttpRequestBuilder<T> queryParams(Map<String, ?> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public HttpRequestBuilder<T> body(Object body) {
        this.body = body;
        return this;
    }

    public HttpRequestBuilder<T> responseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    public HttpRequestBuilder<T> useServiceToken(TokenService tokenService) {
        String token = tokenService.getServiceToken();
        requestContext.setToken(token);
        return this;
    }

    public HttpRequestBuilder<T> useUserToken() {
        requestContext.setToken(Context.getUserToken());
        return this;
    }

    // GET request
    public ResponseEntity<T> get() {
        this.method = HttpMethod.GET;
        return execute();
    }

    // POST request
    public ResponseEntity<T> post() {
        this.method = HttpMethod.POST;
        return execute();
    }

    // PUT request
    public ResponseEntity<T> put() {
        this.method = HttpMethod.PUT;
        return execute();
    }

    // DELETE request
    public ResponseEntity<T> delete() {
        this.method = HttpMethod.DELETE;
        return execute();
    }

    public ResponseEntity<T> execute() {
        return httpClient.execute(url, method, requestContext, queryParams, body, responseType);
    }

//    public <T> T parseResponse(Class<T> responseType) {
//
//    }
}
