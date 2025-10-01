package me.marensovich.wareznet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for serving static resources.
 * <p>
 * Maps the "/static/**" URL path to the classpath resources under "classpath:/static/".
 * </p>
 *
 * @author marensovich
 * @version 0.1
 * @since 0.1
 */
@Configuration
public class StaticResourceConfig {

    /**
     * Configures resource handlers for static files.
     *
     * @return a {@link WebMvcConfigurer} with resource handler settings
     * @since 0.1
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/");
            }
        };
    }
}