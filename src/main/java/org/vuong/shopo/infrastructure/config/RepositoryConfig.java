package org.vuong.shopo.infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.vuong.shopo.infrastructure.projection.CategoryProjection;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors
    ) {
//        config.useHalAsDefaultJsonMediaType(false);
        Class<?>[] classes = entityManager.getMetamodel()
                .getEntities().stream().map(Type::getJavaType).toArray(Class[]::new);
        config.exposeIdsFor(classes);
        config.getProjectionConfiguration().addProjection(CategoryProjection.class);
    }

//    @Bean
//    public RepositoryRestConfigurer repositoryRestConfigurer() {
//        return RepositoryRestConfigurer.withConfig(config -> config
//                .exposeIdsFor(entityManager.getMetamodel()
//                        .getEntities()
//                        .stream()
//                        .map(Type::getJavaType)
//                        .toArray(Class[]::new)
//                )
//        );
//    }
}
