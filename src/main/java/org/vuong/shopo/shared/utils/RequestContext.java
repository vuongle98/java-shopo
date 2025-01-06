package org.vuong.shopo.shared.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.function.Consumer;

@Setter
@Getter
public class RequestContext {

    private String token; // Authorization token
    private Map<String, String> additionalHeaders; // Custom headers
    private Consumer<Exception> exceptionHandler; // Custom error handling
}
