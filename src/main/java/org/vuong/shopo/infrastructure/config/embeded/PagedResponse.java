package org.vuong.shopo.infrastructure.config.embeded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.PagedModel;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> implements Serializable {

    private Collection<T> content;
    private PagedModel.PageMetadata page;
}
