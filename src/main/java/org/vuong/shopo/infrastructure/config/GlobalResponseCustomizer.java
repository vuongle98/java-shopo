package org.vuong.shopo.infrastructure.config;


import org.springframework.core.MethodParameter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.vuong.shopo.infrastructure.config.embeded.PagedResponse;
import org.vuong.shopo.shared.utils.ResponseUtil;

@ControllerAdvice
public class GlobalResponseCustomizer implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType contentType,
            Class<? extends HttpMessageConverter<?>> converterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (body instanceof PagedModel<?> pagedModel) {
            return ResponseUtil.success(new PagedResponse<>(pagedModel.getContent(), pagedModel.getMetadata()));
        }

        if (body instanceof CollectionModel<?> collectionModel) {
            return ResponseUtil.success(collectionModel.getContent());
        }

        if (body instanceof EntityModel<?> entityModel) {
            return ResponseUtil.success(entityModel);
        }

        return body;
    }
}
