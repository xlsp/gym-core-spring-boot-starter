package cn.arcdev.core.autoconfigure;

import cn.arcdev.core.exception.handler.CoreGlobalExceptionHandler;
import cn.arcdev.core.exception.handler.CoreResponseEntityExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for gym core.
 *
 * @author Kraken
 */
@Configuration
public class CoreConfig {

    /**
     * A customized {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler},
     * replaces the default one in spring boot web.
     *
     * @return A customized {@code CoreResponseEntityExceptionHandler} bean.
     */
    @Bean
    public CoreResponseEntityExceptionHandler responseEntityExceptionHandlerBean() {
        return new CoreResponseEntityExceptionHandler();
    }

    /**
     * Global exception handler.
     *
     * @return {@code CoreGlobalExceptionHandler} bean.
     */
    @Bean
    public CoreGlobalExceptionHandler coreGlobalExceptionHandlerBean() {
        return new CoreGlobalExceptionHandler();
    }
}
