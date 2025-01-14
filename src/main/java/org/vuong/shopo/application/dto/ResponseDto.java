package org.vuong.shopo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> extends RepresentationModel<ResponseDto<T>> implements Serializable {

    private int code;
    private String message;
    private T data;
}
