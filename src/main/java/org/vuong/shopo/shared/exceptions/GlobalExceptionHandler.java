package org.vuong.shopo.shared.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vuong.shopo.application.dto.ResponseDto;
import org.vuong.shopo.shared.utils.ResponseUtil;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<String>> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(
                ResponseUtil.error(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
