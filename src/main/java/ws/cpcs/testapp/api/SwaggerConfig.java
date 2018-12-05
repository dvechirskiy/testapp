package ws.cpcs.testapp.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Collections.emptyList;

@EnableSwagger2
@Configuration
@PropertySource("classpath:swagger.properties")
class SwaggerConfig {

    @Value("${app.version}")
    private String version;

    @Value("${app.public.url}")
    private String host;

    @Value("${app.description}")
    private String description;


    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ws.cpcs.testapp.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .host(host)
                .apiInfo(new ApiInfo(
                        "Testapp API",
                        description,
                        version,
                        null,
                        null,
                        null,
                        null,
                        emptyList()
                ));
    }
}