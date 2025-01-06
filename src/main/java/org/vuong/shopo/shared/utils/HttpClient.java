package org.vuong.shopo.shared.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClient extends AbstractHttpClient {

    public HttpClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected void preProcess(RequestContext requestContext) {
        super.preProcess(requestContext);
        String clientToken = Context.getToken();
        requestContext.setToken(clientToken);
    }

    @Override
    protected HttpHeaders createHeaders(RequestContext requestContext) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (requestContext.getToken() != null) {
            headers.setBearerAuth(requestContext.getToken());
        }
        if (requestContext.getAdditionalHeaders() != null) {
            requestContext.getAdditionalHeaders().forEach(headers::set);
        }
        return headers;
    }

    @Override
    protected void handleException(Exception exception, RequestContext requestContext) {
        if (requestContext.getExceptionHandler() != null) {
            requestContext.getExceptionHandler().accept(exception);
        } else {
            System.err.println("Default Error Handling: " + exception.getMessage());
        }
    }
}
