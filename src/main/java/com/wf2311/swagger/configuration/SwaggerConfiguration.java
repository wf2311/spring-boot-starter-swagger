package com.wf2311.swagger.configuration;

import com.wf2311.swagger.properties.SwaggerConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {
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
                if (properties.getRedirect()) {
                    registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
                }
//                if (properties.getApiUrl() != null && !"".equals(properties.getApiUrl().trim())) {
//                    registry.addViewController(properties.getApiUrl())
//                            .setViewName("/swagger-ui.html");
//                }
            }
        };
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .alternateTypeRules(properties.getAlternateTypeRules())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/webjars/");

        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/static/swagger-ui.html");
//        if (properties.getApiUrl() != null && !"".equals(properties.getApiUrl().trim())) {
//            registry.addResourceHandler(properties.getApiUrl())
//                    .addResourceLocations("classpath:/static/swagger-ui.html");
//        }

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .build();
    }
}
