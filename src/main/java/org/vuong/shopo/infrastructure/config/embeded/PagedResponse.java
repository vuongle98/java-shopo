package org.vuong.shopo.infrastructure.config.embeded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.core.EmbeddedWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class PagedResponse<T> implements Serializable {

    private Collection<T> content;
    private PagedModel.PageMetadata page;

    public PagedResponse(Collection<T> content, PagedModel.PageMetadata page) {
        this.content = isEmpty(content) ? new ArrayList<>() : content;
        this.page = page;
    }

    private boolean isEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }

        return collection.stream().allMatch(item -> item instanceof EmbeddedWrapper);
    }
}
