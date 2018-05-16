package com.wf2311.swagger.spring.boot.starter.annotation;

import com.wf2311.swagger.spring.boot.starter.configuration.SwaggerConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.lang.annotation.*;


/**
 * @author wf2311
 * @date 2017/04/25 20:31.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@Import({Swagger2DocumentationConfiguration.class, SwaggerConfiguration.class})
public @interface EnableWfSwagger {
}
