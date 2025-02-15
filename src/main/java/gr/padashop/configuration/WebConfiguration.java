package gr.padashop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration //activate this to serve static public files
public class WebConfiguration implements WebMvcConfigurer {
    private final AppConfig config;

    public WebConfiguration(AppConfig config) {
        this.config = config;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //server all files under resources/static
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/public", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+ (config.getImageFilesPath()));
    }
}


//source: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/static-resources.html