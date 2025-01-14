package org.vuong.shopo.shared.utils;

import org.springframework.http.HttpStatus;
import org.vuong.shopo.application.dto.ResponseDto;

public class ResponseUtil {

    public static <T> ResponseDto<T> success(T data, String message) {

        return ResponseDto
                .<T>builder()
                .code(HttpStatus.OK.value())
                .data(data)
                .message(message)
                .build();
    }

    public static <T> ResponseDto<T> success(T data) {

        return ResponseDto
                .<T>builder()
                .code(HttpStatus.OK.value())
                .data(data)
                .message("Process successful")
                .build();
    }

    public static <T> ResponseDto<T> error(HttpStatus status, String message) {
        return ResponseDto
                .<T>builder()
                .code(status.value())
                .message(message)
                .data(null)
                .build();
    }
}
