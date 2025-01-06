package org.vuong.shopo.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataExistedException extends RuntimeException {

    public DataExistedException(String message) {
        super(message);
    }
}

