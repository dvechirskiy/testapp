package ws.cpcs.testapp.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("ws.cpcs.testapp.api")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String SWAGGER_UI = "/swagger-ui";

    @Bean
    RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", SWAGGER_UI + "/index.html");
        registry.addRedirectViewController(SWAGGER_UI, SWAGGER_UI + "/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(SWAGGER_UI +"/**").addResourceLocations("classpath:/swagger-ui/");
    }
}
