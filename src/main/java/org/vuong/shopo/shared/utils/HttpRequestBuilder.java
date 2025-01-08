package org.vuong.shopo.shared.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.vuong.shopo.shared.services.TokenService;

import java.util.Map;

public class HttpRequestBuilder<T> {

    private final HttpClient httpClient;
    private String url;
    private HttpMethod method;
    private final RequestContext requestContext = new RequestContext();
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
}
