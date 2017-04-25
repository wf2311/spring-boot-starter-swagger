package com.wf2311.swagger;

import com.wf2311.swagger.configuration.SwaggerConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@Import({Swagger2DocumentationConfiguration.class, SwaggerConfiguration.class})
public @interface EnableWfSwagger {
}
