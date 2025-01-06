package org.vuong.shopo.shared.utils;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public abstract class AbstractHttpClient {

    protected final RestTemplate restTemplate;

    public AbstractHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Creates headers dynamically based on the request.
     *
     * @param requestContext Contextual information for the request (e.g., token, user data).
     * @return Configured HttpHeaders.
     */
    protected abstract HttpHeaders createHeaders(RequestContext requestContext);

    /**
     * Handles exceptions dynamically.
     *
     * @param exception The exception that occurred.
     * @param requestContext Contextual information for error handling.
     */
    protected abstract void handleException(Exception exception, RequestContext requestContext);

    /**
     * Pre-processes the request before sending.
     *
     * @param requestContext The request context.
     */
    protected void preProcess(RequestContext requestContext) {
        // Optional hook for subclasses
    }

    /**
     * Post-processes the response after receiving it.
     *
     * @param response The response entity.
     * @param requestContext The request context.
     */
    protected <T> void postProcess(ResponseEntity<T> response, RequestContext requestContext) {
        // Optional hook for subclasses
    }

    /**
     * Core method to execute HTTP requests.
     */
    public <T> ResponseEntity<T> execute(
            String url,
            HttpMethod method,
            RequestContext requestContext,
            Map<String, ?> queryParams,
            Object body,
            Class<T> responseType
    ) {
        try {
            preProcess(requestContext);

            // Build URI with query parameters
            URI uri = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParams(queryParams != null ? convertToMultiValueMap(queryParams) : null)
                    .build()
                    .toUri();

            // Create headers and entity
            HttpHeaders headers = createHeaders(requestContext);
            HttpEntity<Object> entity = new HttpEntity<>(body, headers);

            // Execute request
            ResponseEntity<T> response = restTemplate.exchange(uri, method, entity, responseType);

            postProcess(response, requestContext);
            return response;
        } catch (Exception e) {
            handleException(e, requestContext);
            throw e; // Re-throw to propagate
        }
    }

    /**
     * Converts a Map to MultiValueMap for query parameters.
     */
    private MultiValueMap<String, String> convertToMultiValueMap(Map<String, ?> queryParams) {
        MultiValueMap<String, String> multiValueMap = new org.springframework.util.LinkedMultiValueMap<>();
        if (queryParams != null) {
            queryParams.forEach((key, value) -> {
                if (value instanceof Iterable) {
                    for (Object v : (Iterable<?>) value) {
                        multiValueMap.add(key, v.toString());
                    }
                } else {
                    multiValueMap.add(key, value.toString());
                }
            });
        }
        return multiValueMap;
    }
}
