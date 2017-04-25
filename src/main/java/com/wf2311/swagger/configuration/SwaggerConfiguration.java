package com.wf2311.swagger.configuration;

import com.fasterxml.classmate.TypeResolver;
import com.wf2311.swagger.properties.SwaggerConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangfeng
 * @time 2017/04/25 20:31.
 */
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
public class SwaggerConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    private final SwaggerConfigurationProperties properties;

    public SwaggerConfiguration(final SwaggerConfigurationProperties properties) {
        this.properties = properties;

        log.info("using springfox.swagger2 with properties='{}'", properties);
    }

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                if (properties.getRedirect() != null && properties.getRedirect().trim() != "") {
                    registry.addViewController(properties.getRedirect()).setViewName("redirect:/swagger-ui.html");
                }
                registry.addViewController("/webjars/**")
                        .setViewName("classpath:/webjars/");

                registry.addViewController("/swagger-ui.html**")
                        .setViewName("classpath:/static/swagger-ui.html");
            }
        };
    }

    @Bean
    public Docket docket(final TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(properties.getAlternateTypeRules())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .build();
    }
}
